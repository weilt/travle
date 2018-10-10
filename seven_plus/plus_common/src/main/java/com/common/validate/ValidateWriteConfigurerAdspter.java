package com.common.validate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 这个直接 添加拦截
 * @author LiuGang
 */
@Configuration   //WebMvcConfigurerAdapter
public class ValidateWriteConfigurerAdspter extends WebMvcConfigurerAdapter{

	@Bean
	ValidateWriteInFormality localInterceptor() {
		return new ValidateWriteInFormality();
	}
	/**
	 * 添加拦截 -
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
