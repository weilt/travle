package com.hx.bureau.config;

import com.hx.bureau.handler.RequestInterceptHandler;
import com.hx.core.utils.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

//import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = "com.xx")
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	RequestInterceptHandler localInterceptor() {
		return new RequestInterceptHandler();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 *  多个拦截器组成一个拦截器链
		 *  addPathPatterns 用于添加拦截规则
		 *  excludePathPatterns 用户排除拦截
		 */
		registry.addInterceptor(localInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/admin")
				.excludePathPatterns("/")
				.excludePathPatterns("/loginOut");
		super.addInterceptors(registry);	
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController( "/" ).setViewName( "forward:/login.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers( registry );
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		super.configurePathMatch(configurer);
		configurer.setUseSuffixPatternMatch(false);
	}

	/**
	 * 配置资源文件的映射
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String uploadPath = PropertiesUtils.getValue("web.upload-path", "application.properties"); //
		
		ResourceHandlerRegistration handlerRegistration = registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		
		if(StringUtils.isNotBlank(uploadPath)){
			handlerRegistration.addResourceLocations("file:"+uploadPath);
		}

		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");

		super.addResourceHandlers(registry);
	}
	/*
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}*/
	
	
}
