package wechat.listener;

import java.util.Date;
import java.util.HashMap;

import wechat.process.AccessTokenApiProcess;

public class AccessTokenCache {

	private static HashMap<String, Object> map = new HashMap<>(); // cache
	
	private static final String ACCESS_TOKEN = "token";

	private static final String OVERDUE_TIME = "time";

	public void initToken() {
		map.put(OVERDUE_TIME, new Date().getTime());
		map.put(ACCESS_TOKEN, new AccessTokenApiProcess().getAccessTokenResult());
	}

	public static String getToken() {
		Long time = (Long) map.get(OVERDUE_TIME);
		if (!permitTime(time)) {
			map.put(OVERDUE_TIME, new Date().getTime());
			map.put(ACCESS_TOKEN, new AccessTokenApiProcess().getAccessTokenResult());
		}
		return (String) map.get(ACCESS_TOKEN);
	}

	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		Long time = new Date().getTime();
		System.out.println("new time: " +  new Date().getTime());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date().getTime()- time);
	}

	private static boolean permitTime(Long time) {
		Long currentTime = new Date().getTime();
		if (time != null && currentTime - time < 7000000) 
			return true;
		return false;
	}
}
