package com.hx.admin.controller.adminHx.order;

import com.hx.order.service.admin.AdminOrderService;
import com.hx.order.service.admin.impl.AdminOrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class factory {
    @Bean
    public AdminOrderService adminOrderService(){
        return new AdminOrderServiceImpl();
    }

}
