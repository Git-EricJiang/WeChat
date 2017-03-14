package demo.process;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class TulingApiProcess {

	/**
	 * 调用机器人api接口，获取智能回复内容，解析获取自己所需结果
	 * 
	 * @param content
	 * @return
	 */
	public String getTulingResult(String content) {
		String apiUrl = "http://www.tuling123.com/openapi/api?key=b24ccf134297448889031c194e60316e&info=";

		String param = "";
		try {
			param = apiUrl + URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 将参数转为url编码

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
			result = "对不起,我不能理解您的意思";
		}
		try {
			JSONObject json = new JSONObject(result);
			if (100000 == json.getInt("code")) {
				result = json.getString("text");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
