package wechat.process;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import wechat.listener.AccessTokenCache;

public class UserInfoApiProcess {

	public static void main(String[] args) throws ClientProtocolException, IOException {
//		String apiUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa28ef2be12d087d4&secret=ba9ce30c6d0780ec0a7179035fb02480";
//
//		String param = null;
//		param = apiUrl + URLEncoder.encode("", "UTF-8");
//
//		HttpGet request = new HttpGet(param);
//		HttpResponse response = HttpClients.createDefault().execute(request);
//		HttpResponse response1 = HttpClients.createMinimal().execute(request);
//		System.out.println(response);
//		System.out.println(response1);
		System.out.println(isSubscribe(""));
	}

	public static boolean isSubscribe(String openId) throws ClientProtocolException, IOException {

		String apiUrl = String.format(
				"https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN",
				AccessTokenCache.getToken(), openId);
//		String apiUrl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa28ef2be12d087d4&secret=ba9ce30c6d0780ec0a7179035fb02480");
		
		String param = apiUrl + URLEncoder.encode("", "UTF-8");
		HttpGet request = new HttpGet(param);
		HttpResponse response = HttpClients.createMinimal().execute(request);
		HttpEntity entity = response.getEntity();
		
		String result = EntityUtils.toString(entity);
		
		if (result != null) {
//			result = "UserInfoApiProcess error";
			JSONObject js = new JSONObject(result);
			System.out.println("UserInfoApiProcess: " + js);System.out.println();
			int i = js.getInt("subscribe");
			if (i == 1) {
				return true;
			} 
		}
		return false;
	}
}
