package com.hx.core.logs.annotation;

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
public @interface Log {
	
	/**
	 * LOGIN 		登录
	 * OPERATION	操作
	 * @author WJ
	 */
	public enum LogType{LOGIN, OPERATION};
	
	String dec();
	
	LogType logType() default LogType.OPERATION;

	
}

