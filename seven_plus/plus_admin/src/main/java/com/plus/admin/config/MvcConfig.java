package com.plus.admin.config;

import com.common.config.WechatJwtConfig;
import com.common.jwt.provider.JwtTokenProvider;
import com.common.util.ObjectHelper;
import com.common.util.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

/**
 *
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public APIFilter apiFilter(){
		return new APIFilter();
	}

	/**
	 * Filter中要用到spring的bean 就必须这样配置
	 * @return
	 */
	@Bean
	public FilterRegistrationBean demoFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(apiFilter());
		registration.addUrlPatterns("/api/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("apiFilter");
		return registration;
	}

	/**
	 *  多个拦截器组成一个拦截器链
	 *  addPathPatterns 用于添加拦截规则
	 *  excludePathPatterns 用户排除拦截
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController( "/" ).setViewName( "forward:/back");
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
		
		ResourceHandlerRegistration handlerRegistration = registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
		
		if(ObjectHelper.isNotEmpty(uploadPath)){
			handlerRegistration.addResourceLocations("file:"+uploadPath);
		}

		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}


	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	
}
