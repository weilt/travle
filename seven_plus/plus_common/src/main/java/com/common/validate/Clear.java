package com.common.validate;

import java.lang.annotation.*;


/**
 * 清除验证  - 自定义的一些验证信息
 * @author JIAO_LIU_BABA
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Clear {
	
}
