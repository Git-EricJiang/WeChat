package wechat.process;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import wechat.listener.AccessTokenCache;

public class RequstApiProcess {

	private static String GET_TOKEN = AccessTokenCache.getToken();

	public static Object getRequstApi(String url) {

		String urlApi = url.concat(GET_TOKEN);
		String param = "";
		try {
			param = urlApi + URLEncoder.encode("", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String result = "";

		HttpGet request = new HttpGet(param);
		try {
			HttpResponse response = HttpClients.createDefault().execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (result != null) {
			System.out.println("result: " + result);
			JSONObject json = new JSONObject(result);
//			JSONObject json = JSONObject.fromObject(request);
			System.out.println("json: " + json.get("menu"));
		}
		return result;
	}

	public static Object postRequst(String url) {
		String apiUrl = url.concat(GET_TOKEN);
		String param = "";
		try {
			param = URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	public static void main(String[] args) {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
		RequstApiProcess.getRequstApi(url);
	}
}
