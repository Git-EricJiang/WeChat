package wechat.process;

public class InvokeApiProcess {

	public String processApi(String xml) {
		
		String result = "";
		result = new AccessTokenApiProcess().getAccessTokenResult();
		
		return result;
				
	}
}
