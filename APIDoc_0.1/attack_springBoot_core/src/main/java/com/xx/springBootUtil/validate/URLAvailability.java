package com.xx.springBootUtil.validate;

import java.net.HttpURLConnection;
import java.net.URL;

public class URLAvailability {

	/**
	 * 功能描述 : 检测当前URL是否可连接或是否有效, 最多连接网络 5 次, 如果 5 次都不成功说明该地址不存在或视为无效地址.
	 * 
	 * @param url
	 *            指定URL网络地址
	 * 
	 * @return String
	 */
	public synchronized boolean isConnect(String url) {
		int counts = 0;
		boolean b = false;
		if (url == null || url.length() <= 0) {
			return b;
		}
		while (counts < 5) {
			try {
				URL urlStr = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) urlStr.openConnection();
				int state = connection.getResponseCode();
				if (state == 200) {
					b = true;
				}
				break;
			} catch (Exception ex) {
				counts++;
				continue;
			}
		}
		return b;
	}
	
	/**
	 * 功能描述 : 检测当前URL是否可连接或是否有效
	 * @param URLName  指定URL网络地址
	 * @return String
	 */
 	public static boolean exists(String URLName) {
       try {
           //设置此类是否应该自动执行 HTTP 重定向（响应代码为 3xx 的请求）。
           HttpURLConnection.setFollowRedirects(false);
           //到 URL 所引用的远程对象的连接
           HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
           /* 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE 以上方法之一是合法的，具体取决于协议的限制。*/
           con.setRequestMethod("HEAD");
           //从 HTTP 响应消息获取状态码
           return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
       } catch (Exception e) {
           return false;
        }
    }
}
