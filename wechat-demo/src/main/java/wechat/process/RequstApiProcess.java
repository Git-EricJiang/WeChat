package wechat.process;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import wechat.entity.GroupEntity;
import wechat.listener.AccessTokenCache;

public class RequstApiProcess {

	 private static String GET_TOKEN = AccessTokenCache.getToken();
//	private static String GET_TOKEN = "C6RMWOtxuQ7Exk4nWB5zJFo-1e5T33aTnm-8sC6_DJt1HOjln8T8mXeI9zqZq6365xVyd8OYhOCpWZ_hoZWaFVCZw4emks2v-fdwFfxSwH0ZEWdAHATYQ";

	/**
	 * 
	 * @param url url %s
	 * @param jsonKey
	 * @return
	 */
	public static Object getRequstApi(String url, String jsonKey) {
		String urlApi = String.format(url, GET_TOKEN);
//		String urlApi = url.concat(GET_TOKEN);
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
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (result != null) {
			System.out.println("\nresult: " + result);
			JSONObject json = new JSONObject(result);
			// JSONObject json = JSONObject.fromObject(request);
			if (jsonKey != null && !"".equals(jsonKey)) {
//				System.out.println("\njson: " + json.get(jsonKey));
			}
		}
		return result;
	}

	public static Object postRequstByOrgApache(String url, GroupEntity entity) {
		String param = new JSONObject(entity).toString();
		String apiUrl = url.concat(GET_TOKEN);
		HttpPost httpPost = new HttpPost(apiUrl);

		StringEntity stringEntity = new StringEntity(param, "UTF-8");
		stringEntity.setContentEncoding("UTF-8");
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		String result = "";
		try {
			HttpResponse response = HttpClients.createDefault().execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {

				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println("result: " + result);
		JSONObject json = new JSONObject(result);
		System.out.println("\njson: " + json);

		return result;
	}

	public static void postRequstApiByJavaNet(String apiUrl, GroupEntity entity) throws IOException {

		// String urlParam =
		// JSONObject.fromObject(WechatEntityUtils.getGroupEntity()).toString();

		String requestUrl = apiUrl.concat(GET_TOKEN);
		String urlParam = new JSONObject(entity).toString();
		System.err.println(urlParam);
		// Charset.forName("UTF-8");
		byte[] postData = urlParam.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;

		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// conn.setRequestProperty("Content-Type", "json");
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-length", Integer.toString(postDataLength));
		conn.setUseCaches(false);

		try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
			wr.write(postData);
			wr.flush();
			wr.close();
		}
		// int code = conn.getResponseCode();

		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str = "";
		while ((str = br.readLine()) != null) {
			System.err.println(str);
		}

		conn.disconnect();
	}


	public static void main(String[] args) throws IOException {
		String createUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=";
		String deleteUrl = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=";
		String uploadpath = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=C6RMWOtxuQ7Exk4nWB5zJFo-1e5T33aTnm-8sC6_DJt1HOjln8T8mXeI9zqZq6365xVyd8OYhOCpWZ_hoZWaFVCZw4emks2v-fdwFfxSwH0ZEWdAHATYQ&type=image";
		//查询分组
//		RequstApiProcess.getRequstApi("https://api.weixin.qq.com/cgi-bin/groups/get?access_token=%s", "groups");
		//创建新分组
//		RequstApiProcess.postRequst(createUrl, WechatEntityUtils.createGroupByName("newGroup"));
		//根据ID删除分组
//		RequstApiProcess.postRequstByOrgApache(deleteUrl, WechatEntityUtils.deleteGroupById(2));

		//查询分组
//		RequstApiProcess.getRequstApi("https://api.weixin.qq.com/cgi-bin/groups/get?access_token=%s", "groups");
				
		
//		RequstApiProcess.getRequstApi("https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s", "data");
//		RequstApiProcess.getRequstApi("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=ovnM-w5jzO5MGzg2jvpqdg4HmlLU&lang=zh_CN",null);

	}
}
