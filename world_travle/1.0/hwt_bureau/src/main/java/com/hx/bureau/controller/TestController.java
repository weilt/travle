package com.hx.bureau.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/1 11:35
 * @Description:
 */
@RestController
public class TestController {


    @GetMapping("/")
    public String index(){
        return "INDEX";
    }
}
