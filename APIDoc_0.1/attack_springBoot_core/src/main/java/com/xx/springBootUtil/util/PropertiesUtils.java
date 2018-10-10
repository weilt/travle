package com.xx.springBootUtil.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取某个文件的数据信息
 * @author LiuGang
 *
 */
public class PropertiesUtils {
	
	public static Properties getProperties(String properties) {
		Properties p = new Properties();
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(properties);
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p;
	}
	
	public static String getValue(String key,String url) {
		Properties p = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(url);
			p.load(inputStream);
			return p.getProperty(key);
		} catch (IOException e1) {
//			e1.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
}
