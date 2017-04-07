package demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import wechat.process.RequstApiProcess;

public class ResponseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String APPID = "";
	private static final String SECRET = "";

	/**
	 * 关于网页授权回调域名
	 * 用户同意授权，获取code
	 * 
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getQueryString());
		String code = req.getParameter("code");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";
		
		String rs = RequstApiProcess.getRequstApi(url, null)+"";
		JSONObject json = new JSONObject(rs);
		
		String ul = "https://api.weixin.qq.com/sns/userinfo?access_token="+ json.getString("access_token") +"&openid="+ json.getString("openid") +"&lang=zh_CN";
		String openid = RequstApiProcess.getRequstApi(ul, null)+"";
		System.out.println(openid);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
