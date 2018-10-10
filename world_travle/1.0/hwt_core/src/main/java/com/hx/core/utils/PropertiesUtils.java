package com.hx.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取某个文件的数据信息
 * @author LiuGang
 *
 */
public class PropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	public static Properties getProperties() {
		Properties p = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(
					"RestAPIConfig.properties");
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p;
	}
	
	public static Properties getProperties(String url) {
		Properties p = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(url);
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
			e1.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(url + ",文件流关闭出现异常");
				}
			}
		}
		return null;
	}

	public static String getValue(String key) {
		Properties p = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(
					"my-config.properties");
			p.load(inputStream);
			return p.getProperty(key);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("关闭文件流时异常!");
				}
			}
		}
		return null;
	}
}
