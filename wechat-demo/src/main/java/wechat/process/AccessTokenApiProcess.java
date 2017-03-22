package wechat.process;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class AccessTokenApiProcess {

	private static final String APPID = "APPID";// 换成自己的
	private static final String SECRET = "SECRET";// 换成自己的

	public String getAccessTokenResult() {
		String apiUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID
				+ "&secret=" + SECRET;

		String param = "";
		try {
			param = apiUrl + URLEncoder.encode("", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		HttpGet request = new HttpGet(param);
		String result = "";
		try {
			HttpResponse response = HttpClients.createDefault().execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == result) {
			result = "AccessTokenApiProcess error";
		}
		try {
			JSONObject json = new JSONObject(result);
			// Gson gs = new Gson();
			// String jsonObject = gs.toJson(result);
			/*
			 * if (100000 == json.getInt("code")) { result =
			 * json.getString("text"); }
			 */
			System.out.println("json access_token: " + json.getString("access_token"));
			result = json.getString("access_token");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
