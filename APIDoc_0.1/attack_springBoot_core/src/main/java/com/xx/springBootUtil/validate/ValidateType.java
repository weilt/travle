package com.xx.springBootUtil.validate;
/**
 * 参数验证信息
 * @author JIAO_LIU_BABA
 */
public enum ValidateType {
	/**
	 * NULL 		为空验证
	 * PHONE 		手机验证
	 * EMAIL 		邮箱验证
	 * IdCard 		身份证验证
	 * Digit 		正整数或者负整数验证
	 * Decimals 	小数验证
	 * TOKEN		登录验证
	 */
	NULL, PHONE, EMAIL, IdCard, Digit, Decimals,TOKEN
}
