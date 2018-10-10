package com.hx.admin.controller.adminHx.order;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Api(value = "API - OrderController", description = "退款订单管理")
public class HxGuideOrderController {
    /**
     * 跳转到订单退款管理界面
     *
     * @return
     */
//    @RequestMapping("admin/order/list")
//    public ModelAndView list() {
//        return new ModelAndView("adminHx/order/order-list");
//    }
}
