package com.hx.admin.controller;

import com.hx.admin.base.BasePage;
import com.hx.core.utils.DateUtils;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by Administrator on 2018/4/17.
 * 后台示例模块
 */
@RestController
@RequestMapping("/model")
public class ModelController {


    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/model/index");
    }

    @RequestMapping("/datatable")
    public ModelAndView datatable() {
        return new ModelAndView("/model/table-datatable");
    }

    @RequestMapping("/fromTable")
    public ModelAndView fromTable() {
        return new ModelAndView("/model/fromTable");
    }

    @RequestMapping("/customForm")
    public ModelAndView customForm() {
        return new ModelAndView("/model/customForm");
    }

    @RequestMapping("/customDatatable")
    public ModelAndView customDatatable() {
        return new ModelAndView("/model/customDatatable");
    }

    @RequestMapping("/getData")
    public Map getData(BasePage page) {
        System.out.println(JsonUtils.Bean2Json(page));
        List<Map<String,Object>> l = new ArrayList<>();


        Map<String,Object> resultMap = new HashMap<>();
        for (int i = page.getStartNum(); i <= page.getStartNum() + page.getPageSize(); i++) {
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("testId",i);
            dataMap.put("testName","testName_" + i);
            dataMap.put("sex", i%2 == 0 ? 1 : 2);
            dataMap.put("datetime", DateUtils.getDateHtml(new Date()));
            dataMap.put("token", RandomUtils.randomNumber(16));
            l.add(dataMap);
        }

        //返回结果
        resultMap.put("dataList",l);
        //返回总数
        resultMap.put("count",10);

        return resultMap;
    }

}
