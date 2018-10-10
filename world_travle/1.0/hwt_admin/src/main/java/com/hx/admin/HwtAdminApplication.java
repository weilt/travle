package com.hx.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ServletComponentScan
@ComponentScan(basePackages = {"com.hx.admin","com.hwt.domain","com.hx.core","com.hx.adminHx","com.hx.system",
								"com.hx.scenic.adminHx","com.hx.bureau","com.hx.information"})
@MapperScan(basePackages = "com.hwt.domain.mapper")
@EnableAsync
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class HwtAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwtAdminApplication.class, args);
	}
}
	
