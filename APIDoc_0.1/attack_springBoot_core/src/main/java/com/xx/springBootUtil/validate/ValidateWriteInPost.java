package com.xx.springBootUtil.validate;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 验证 - 参数信息
 * @author LiuGang
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ValidateWriteInPost {
//	Class<? extends HandlerInterceptor>[] value() default ValidateWriteInFormality.class;
	/**
	 * NULL 		为空验证
	 * PHONE 		手机验证
	 * EMAIL 		邮箱验证
	 * IdCard 		身份证验证
	 * Digit 		正整数或者负整数验证
	 * Decimals 	小数验证
	 * TOKEN		登录验证
	 */
//	public enum ValidateType{NULL, PHONE, EMAIL, IdCard, Digit, Decimals,TOKEN};
	
	/**
	 * 参数
	 * @return
	 */
	String[] parameter();//参数
	
	/**
	 * 类型
	 * NULL 		为空验证
	 * PHONE 		手机验证
	 * EMAIL 		邮箱验证
	 * IdCard 		身份证验证
	 * Digit 		正整数或者负整数验证
	 * Decimals 	小数验证
	 * TOKEN		登录验证
	 * @return
	 */
	ValidateType[] type() default {}; //类型
	
	/**
	 * 返回的条件信息
	 * @return
	 */
	String[] msg() default {}; //返回的条件信息
	
}
