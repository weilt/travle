package com.hx.core.utils;

/**
 *
 */

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 随机数生成工具
 * 
 * @author numenzq
 * 
 * @version 2009-8-25
 */
public class RandomUtils {
	private static final Random RAND = new Random();

	/**
	 * 获取手机号的简写
	 * @param phoneNum
	 * @return
	 */
	public static String phoneNumberCast(String phoneNum){
		if(StringUtils.isBlank(phoneNum) || phoneNum.length() < 5)
			return null;
		return phoneNum.substring(0,3)+"***"+phoneNum.substring(phoneNum.length() - 3);
	}

	/**
	 * 返回一个随机整数
	 * 
	 * @param digit
	 *            整数所在范围, <b>n必须为正整数</b>
	 * @return 随机整数
	 */
	public static int nextInt(int digit) {
		if (digit <= 0) {
			throw new IllegalArgumentException("参数错误：随机数位数不能小于零。");
		}
		return RAND.nextInt(digit);
	}

	/**
	 * 返回一个随机整数
	 * 
	 * @return 随机整数
	 */
	public static long nextLong() {
		return RAND.nextLong();
	}

	/**
	 * 产生指定位数随机数0-9
	 * 
	 * @param digit
	 *            随机数位数
	 * @return 随机数字符串
	 */
	public static String randomNumber(int digit) {
		if (digit <= 0) {
			throw new IllegalArgumentException("参数错误：随机数位数不能小于零。");
		}

		String results = String.valueOf(Math.abs(nextLong()));
		while (results.length() < digit) {
			results = String.valueOf(Math.abs(nextLong()));
		}

		return results.substring(0, digit);
	}

	/**
	 * 产生指定位数随机数0-9a-zA-Z
	 * 
	 * @param digit
	 *            随机数位数
	 * @return 随机数字符串
	 */
	public static String randomString(int digit) {
		if (digit <= 0) {
			throw new IllegalArgumentException("参数错误：随机数位数不能小于零。");
		}
		final String key = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
		StringBuilder results = new StringBuilder();

		for (int i = 0; i < digit; i++) {
			results.append(key.charAt(nextInt(key.length())));
		}
		return results.toString();
	}
}
