package demo.diy.menu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import demo.entity.Button;
import demo.entity.CommonButton;
import demo.entity.ComplexButton;
import demo.entity.Menu;
import demo.entity.ViewButton;
import net.sf.json.JSONObject;
import wechat.entity.util.constant.AppInfoConstant;
import wechat.listener.AccessTokenCache;

public class MenuUtil {

	/**
	 * 封装菜单数据
	 */
	public static Menu getMenu() {
		// 首先创建二级菜单
		CommonButton cb_1 = new CommonButton();
		cb_1.setKey("key1");
		cb_1.setName("子菜单1");
		cb_1.setType("click");

		CommonButton cb_2 = new CommonButton();
		cb_2.setKey("key2");
		cb_2.setName("子菜单2");
		cb_2.setType("click");

		// 创建第一个一级菜单
		ComplexButton cx_1 = new ComplexButton();
		cx_1.setName("一级菜单");
		cx_1.setSub_button(new Button[] { cb_1, cb_2 });

		// 继续创建二级菜单
		ViewButton cb_3 = new ViewButton();
		cb_3.setName("授权");
		cb_3.setType("view");
		cb_3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppInfoConstant.APP_ID+"&redirect_uri="+AppInfoConstant.ENCODE_URL+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

		ViewButton cb_4 = new ViewButton();
		cb_4.setName("搜索");
		cb_4.setType("view");
		// 需要使用网页授权获取微信用户的信息
		cb_4.setUrl("http://baidu.com/");

		// 创建第二个一级菜单
		ComplexButton cx_2 = new ComplexButton();
		cx_2.setName("一级菜单");
		cx_2.setSub_button(new Button[] { cb_3, cb_4 });

		// 创建第三个二级菜单
		CommonButton cb_5 = new CommonButton();
		cb_5.setKey("scancode_key1");
		cb_5.setName("扫码");
		cb_5.setType("scancode_push");

		CommonButton cb_6 = new CommonButton();
		cb_6.setKey("pic_key1");
		cb_6.setName("发图");
		cb_6.setType("pic_sysphoto");

		// 创建第三个一级菜单
		ComplexButton cx_3 = new ComplexButton();
		cx_3.setName("一级菜单");
		cx_3.setSub_button(new Button[] { cb_5, cb_6 });

		// 封装菜单数据
		Menu menu = new Menu();
		menu.setButton(new ComplexButton[] { cx_1, cx_2, cx_3 });

		return menu;
	}

	/**
	 * 创建自定义菜单(每天限制1000次)
	 */
	public static int createMenu(Menu menu, String token) {
		String jsonMenu = JSONObject.fromObject(menu).toString();

		int status = 0;

		System.out.println("菜单：" + jsonMenu);
		String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
		try {
			URL url = new URL(path);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(jsonMenu.getBytes("UTF-8"));
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] bt = new byte[size];
			is.read(bt);
			String message = new String(bt, "UTF-8");
			JSONObject jsonMsg = JSONObject.fromObject(message);
			status = Integer.parseInt(jsonMsg.getString("errcode"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * 创建自定义菜单(每天限制1000次)
	 */
	public static int createMenu(Menu menu) {
		String jsonMenu = JSONObject.fromObject(menu).toString();

		int status = 0;

		System.out.println("菜单：" + jsonMenu);
		String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + AccessTokenCache.getToken();
		try {
			URL url = new URL(path);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(jsonMenu.getBytes("UTF-8"));
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] bt = new byte[size];
			is.read(bt);
			String message = new String(bt, "UTF-8");
			JSONObject jsonMsg = JSONObject.fromObject(message);
			status = Integer.parseInt(jsonMsg.getString("errcode"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	private static void sendGetRequest(Menu menu) {
		String jsonMenu = JSONObject.fromObject(menu).toString();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
//				+ "IohIakwjiCRQc12HUql9JNG9krnU8s3Cjx1rywgS448kpXERqnzG7gI5kL0YmO89L1UNk5XxbJiyy_P61Whm4b2a4a5P1uNuWewxBquh2eJuWj39xtEJ4PAQqklRbVFuJCJhAGAWPW";
		+ AccessTokenCache.getToken();

//		String param = "";
//		try {
//			param = url + URLEncoder.encode("", "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HttpGet request = new HttpGet(param);
		HttpPost httpPost = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(jsonMenu,"UTF-8");
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
		
		System.out.println("result: " + result);
		JSONObject json = JSONObject.fromObject(result);
		System.out.println("json: " + json);
	}

	public static void main(String[] args) {
		MenuUtil.sendGetRequest(getMenu());
	}
}
