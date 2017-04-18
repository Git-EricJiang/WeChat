package demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		String encodeUrl = URLEncoder.encode("http://127.0.0.1/response", "utf-8");
//		String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + AppInfoConstant.APP_ID + "&redirect_uri="
//				+ encodeUrl + "&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
		resp.sendRedirect("/jsp/scan.jsp");
		
	
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
