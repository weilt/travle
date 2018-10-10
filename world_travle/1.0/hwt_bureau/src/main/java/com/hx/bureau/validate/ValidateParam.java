package com.hx.bureau.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证请求参数的注解
 * @author WJ
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateParam {
	
	/**
	 * NULL 		为空验证
	 * MOBIL 		手机验证
	 * EMAIL 		邮箱验证
	 * PINT 		正整数验证
	 * INFO_FILET	信息过滤
	 * SIGN			签名验证 验证操作权限
	 * @author WJ
	 */
	public enum CheckedType{NULL, MOBIL, EMAIL, PINT, INFO_FILET, PWD, SIGN,TOKEN};

	String[] message() default {};
	
	String[] field();
	
	CheckedType[] checkedType() default {};
	
}

