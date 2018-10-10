package com.plus.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/2 14:08
 * @Description:
 */
@SpringBootApplication(scanBasePackages = {"com.common.config","com.common.jwt","com.common.pakag","com.common.validate","com.common.wechat.wxpay","com.admin.service","com.plus"})
//@ServletComponentScan
//@ComponentScan(basePackages = {"com.common.config","com.common.jwt","com.common.pakag","com.common.validate","com.common.wechat.wxpay","com.admin.service","com.plus"})
@MapperScan(basePackages = "com.domain")
@EnableSwagger2
public class SevenPlusAdminApplication extends SpringBootServletInitializer {


//public class SevenPlusAdminApplication {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(SevenPlusAdminApplication.class);


        /**
         * war包方式
         */
        Properties properties = new Properties();
        InputStream in = SevenPlusAdminApplication.class.getClassLoader().getResourceAsStream("application.properties");
        properties.load(in);
        SpringApplication app = new SpringApplication(SevenPlusAdminApplication.class);

        app.setDefaultProperties(properties);
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
