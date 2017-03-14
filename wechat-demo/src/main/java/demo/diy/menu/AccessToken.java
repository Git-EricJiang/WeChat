package demo.diy.menu;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;

public class AccessToken {

	private static DateTime getAccessToken_Time;

	private static int expires_Period = 7200;// token过期时间

	private static String mAccessToken;

	public static String accessToken;

	public static void main(String[] args) {
		String url = String.format(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", "wxa28ef2be12d087d4",
				"ba9ce30c6d0780ec0a7179035fb02480");
		
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = HttpClients.createDefault().execute(request);
			System.out.println(response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getAccessToken(String appId, String appSecret) {
		String url = String.format(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId,
				appSecret);
		
		HttpGet request = new HttpGet(url);
		return null;
	}

	private static boolean hasExpired() {
		if (getAccessToken_Time != null) {

			// if (DateTime.now() > getAccessToken_Time.) {
			//
			// }

		}
		return false;
	}
}
