package demo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.codec.digest.DigestUtils;

import demo.diy.menu.utils.MenuUtil;
import wechat.process.InvokeApiProcess;

public class WechatCheckServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		s = br.readLine();
		if (s != null) {
			sb.append(s);
		}

		String xml = sb.toString();

		String signature = request.getParameter("signature");
		String signature2 = checkSignature(request, response);

		if (signature.equals(signature2)) {
			String result = "";
			String echostr = request.getParameter("echostr");
			// 首次接入会进入并返回
			if (echostr != null && echostr.length() > 1) {
				result = echostr;

//				int status = MenuUtil.createMenu(MenuUtil.getMenu());
//				if (status == 0) {
//					System.out.println("菜单创建成功！");
//				} else {
//					System.out.println("菜单创建失败！");
//					System.out.println(status);
//				}
			} else {//处理正常微信流程
				// 获取token 
				result = new InvokeApiProcess().processApi(xml);
			}

			try {
				OutputStream os = response.getOutputStream();
				os.write(result.getBytes());
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("signature: " + signature);
			System.out.println("signature2: " + signature2);
			// 身份验证失败.
			System.out.println("error");
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private String checkSignature(HttpServletRequest request, HttpServletResponse response) {
		String token = "weixin2";
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		List<String> ls = new ArrayList<>();
		ls.add(token);
		ls.add(timestamp);
		ls.add(nonce);
		Collections.sort(ls);
		String signature = DigestUtils.sha1Hex(ls.get(0) + ls.get(1) + ls.get(2));
		System.out.println("timestamp: " + timestamp);
		System.out.println("nonce: " + nonce);
		System.out.println("signature: " + signature);

		return signature;
	}

}
