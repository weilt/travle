package com.xx;

//import com.xx.proxy.config.LoadRedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@ServletComponentScan
@PropertySource("classpath:manager.properties")
@ComponentScan(basePackages = { "com.xx"})
public class StartSpringBootService extends SpringBootServletInitializer {
//public class ConstomerProxyApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication app = new SpringApplication(ConstomerProxyApplication.class);
//		app.addListeners(new LoadRedisProperties());
//		app.run(args);
        /**
         * war包方式
         */
        Properties properties = new Properties();
        InputStream in = StartSpringBootService.class.getClassLoader().getResourceAsStream("application.properties");
        properties.load(in);
        SpringApplication app = new SpringApplication(StartSpringBootService.class);

        app.setDefaultProperties(properties);
//      app.addListeners(new LoadRedisProperties());
        app.run(args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // TODO Auto-generated method stub
        builder.sources(this.getClass());
        return super.configure(builder);
    }
}
