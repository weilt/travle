package com.hx.core.utils;

import java.security.MessageDigest;

/**
 * md5加密工具
 *
 */
public class Md5Utils {
	
	/***
	 * 加密
	 * @param inStr
	 * @return
	 */
	public static String encodeMD5(String inStr) {
		StringBuffer sb = new StringBuffer(32);

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(inStr.getBytes("utf-8"));

			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			return null;
		}

		return sb.toString();
	}
	

}
