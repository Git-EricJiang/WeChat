package wechat.process;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadMedia {

	// private static String GET_TOKEN = AccessTokenCache.getToken();
	private static String GET_TOKEN = "C6RMWOtxuQ7Exk4nWB5zJFo-1e5T33aTnm-8sC6_DJt1HOjln8T8mXeI9zqZq6365xVyd8OYhOCpWZ_hoZWaFVCZw4emks2v-fdwFfxSwH0ZEWdAHATYQ";

	/**
	 * 根据内容类型判断文件扩展名
	 *
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileexpandedName(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}

	/**
	 * 获取媒体文件
	 * 
	 * @param mediaId
	 *            媒体文件id
	 * @param savePath
	 *            文件在本地服务器上的存储路径
	 */
	public static String downloadMedia(String mediaId, String savePath) {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", GET_TOKEN).replace("MEDIA_ID", mediaId);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = getFileexpandedName(conn.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			filePath = savePath + mediaId + fileExt;
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			String info = String.format("下载媒体文件成功，filePath=" + filePath);
			System.out.println(info);
		} catch (Exception e) {
			filePath = null;
			String error = String.format("下载媒体文件失败：%s", e);
			System.out.println(error);
		}
		return filePath;
	}

	public static void main(String[] args) {
		downloadMedia("6GzLmPEOzj_rVgd1Vtyq-nrQcv34EbLdEFK9bgRNCk5PKpSqhXcwW-s7o6AozI9_", "D:\\微信开发");
	}
}
