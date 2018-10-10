package com.hx.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数字处理
 * @author Administrator
 *
 */
public class NumUtils {
	
	/**
	 * 字符串数字转Long   数组
	 */
	public static Long[] strToLong(String[] str){
		if (str.length<1){
			return null;
		}
		List<Long> nums = new ArrayList<>();
		Arrays.asList(str).stream().forEach(l -> 
			nums.add(Long.parseLong(l))
		);
		return nums.toArray(new Long[nums.size()]);
		
	}
	
}
