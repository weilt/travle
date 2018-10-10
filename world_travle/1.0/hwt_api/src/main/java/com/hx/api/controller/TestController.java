//package com.hx.api.controller;
//
//import com.hwt.domain.entity.user.TestUser;
//import com.hx.core.utils.JsonUtils;
//import com.hx.user.service.TestService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * Created by RO on 2018/3/28.
// */
//@RestController
//public class TestController {
//
//    @Resource(name = "testService")
//    private TestService testService;
//
//    @RequestMapping("/test")
//    public void test() {
//        TestUser tu = testService.selectOne();
//        System.out.println(JsonUtils.Bean2Json(tu));
//    }
//}
