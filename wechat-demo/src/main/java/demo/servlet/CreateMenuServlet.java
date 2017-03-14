package demo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.diy.menu.utils.MenuUtil;
import demo.process.WechatProcess;

public class CreateMenuServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		 /** 读取接收到的xml消息 */  
		StringBuffer buff = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			buff.append(s);
		}
		
		String xml = buff.toString();//次即为接收到微信端发送过来的xml数据  
		
		String result = "";
		/** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */  
		String echostr = request.getParameter("echostr");
		
		if (echostr != null && echostr.length() > 1) {
			result = echostr;
			int status = MenuUtil.createMenu(MenuUtil.getMenu());
			if (status == 0) {
				System.out.println("菜单创建成功！");
			} else {
				System.out.println("菜单创建失败！");
			}
		}else {
			 //正常的微信处理流程  
			result = new WechatProcess().processWeChatMag(xml);
		}
		
		try {
			
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
