package com.hx.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ServletComponentScan
@ComponentScan(basePackages = { "com.hx.api","com.hx.user","com.hwt.domain","com.hx.core","com.hx.scenic",
		"com.hx.system","com.hx.order","com.hx.bureau.service.hx"})
@MapperScan(basePackages = "com.hwt.domain.mapper")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@EnableAsync
@EnableSwagger2
//public class HwtApiApplication extends SpringBootServletInitializer {
public class HwtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwtApiApplication.class, args);
		/**
		 * war包方式
		 */
		//-----
//		Properties properties = new Properties();
//		InputStream in = HwtApiApplication.class.getClassLoader().getResourceAsStream("application.properties");
//		try {
//			properties.load(in);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		SpringApplication app = new SpringApplication(HwtApiApplication.class);
//
//		app.setDefaultProperties(properties);
////      app.addListeners(new LoadRedisProperties());
//		app.run(args);
	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		// TODO Auto-generated method stub
//		builder.sources(this.getClass());
//		return super.configure(builder);
//	}
}
