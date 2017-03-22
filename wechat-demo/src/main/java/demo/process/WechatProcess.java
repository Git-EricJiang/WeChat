package demo.process;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import demo.entity.ReceiveXmlEntity;
import wechat.process.UserInfoApiProcess;

/**
 * 微信xml消息处理流程逻辑类
 * 
 */
public class WechatProcess {

	private static String EVENT_SUBSCRIBE = "subscribe";
	private static String EVENT_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 解析处理xml、获取智能回复结果（通过机器人api接口）
	 * 
	 * @param xml
	 *            接收到的微信数据
	 * @return 最终的解析结果（xml格式数据）
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public String processWeChatMag(String xml) throws ClientProtocolException, IOException {
		System.out.println("接收到的微信数据: " + xml);
		System.out.println();
		/** 解析xml数据 */
		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);
		System.out.println("xmlEntity: " + xmlEntity.toString());
		System.out.println();
		/** 以文本消息为例，调用机器人api接口，获取回复内容 */
		String result = "";
//		if ("text".endsWith(xmlEntity.getMsgType())) {
//			result = new TulingApiProcess().getTulingResult(xmlEntity.getContent());
//		}
//		if (EVENT_SUBSCRIBE.equals(xmlEntity.getEvent())) {
//			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
//					returnContent());
//		} else if ("text".endsWith(xmlEntity.getMsgType())) {
//			result = new TulingApiProcess().getTulingResult(xmlEntity.getContent());
//		}

		if (UserInfoApiProcess.isSubscribe(xmlEntity.getFromUserName())) {
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					xmlEntity.getContent());
		} else {
			result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),
					returnContent());
		}

		return result;
	}

	private String returnContent() {
		String rs = "HI. ";
		return rs;
	}
}
