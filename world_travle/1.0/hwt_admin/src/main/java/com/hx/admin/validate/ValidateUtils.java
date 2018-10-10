package com.hx.admin.validate;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 效验工具类
 * 
 * @author WJ
 *
 */
public class ValidateUtils {

	/**
	 * 验证手机格式是否正确
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) throws PatternSyntaxException {
		//String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147))\\d{8}$";
		String regExp = "0?(13|14|15|18|17|19)[0-9]{9}";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	public static void main(String[] args) {
		String s = "19333333333";
		System.out.println(isMobileNO(s));
	}

	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 * 
	 * @param mobiles
	 */
	public static boolean isHKPhoneLegal(String mobiles) throws PatternSyntaxException {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 检测邮箱地址是否合法
	 * 
	 * @param email
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 密码验证
	 * 
	 * @param pwd
	 * @return true合法 false不合法
	 */
	public static boolean isPassword(String pwd) {
		if (StringUtils.isBlank(pwd))
			return false;
		// 数字 字符 特殊字符 包含2种的匹配
//		Pattern p = Pattern.compile("^(?![\\d]+$)(?![a-zA-Z]+$)(?![!#$%^&@*]+$)[\\da-zA-Z!#$%^&@*]{6,18}$");// 复杂匹配
		// 任意字符的简单匹配
		Pattern p = Pattern.compile("^.{3,12}$");// 复杂匹配
		Matcher m = p.matcher(pwd);
		return m.matches();
	}
}
