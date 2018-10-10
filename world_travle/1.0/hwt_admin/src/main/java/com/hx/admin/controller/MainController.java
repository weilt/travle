package com.hx.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.admin.AdminModule;
import com.hx.admin.service.cache.AdminUserCacheTools;

/**
 * Created by Administrator on 2018/4/13.
 */
@RestController
public class MainController {

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView main(HttpServletRequest request) {

    	List<AdminModule> listModule = AdminUserCacheTools.getSessionForModule(request);
        return new ModelAndView("/main").addObject("listModule", listModule);
    }

    /**
     * 跳转到登录
     * @return
     */
    @RequestMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("/login");
    }
    
    /**
     * 跳转到无权限操作页面
     * @return
     */
    @RequestMapping("/powerExpire")
    public ModelAndView powerExpire() {
        return new ModelAndView("/powerExpire");
    }
}
