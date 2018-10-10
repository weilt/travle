package com.hx.bureau.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hx.bureau.base.ResultEntity;
import com.hx.bureau.service.cache.BureauUserCacheTools;
import com.hx.bureau.service.hx.BureauIndexService;
import com.hx.bureau.util.BaseController;

/**
 * Created by Administrator on 2018/4/13.
 */
@RestController
public class MainController extends BaseController{


    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView main(HttpServletRequest request) {
    	List<HxBureauModule> listModule = BureauUserCacheTools.getSessionForModule(request);
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
