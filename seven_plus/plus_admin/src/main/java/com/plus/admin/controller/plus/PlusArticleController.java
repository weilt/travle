package com.plus.admin.controller.plus;

import com.admin.service.plus.PlusArticleService;
import com.common.consts.Consts;
import com.common.pakag.PageInfo;
import com.common.util.JsonUtil;
import com.common.util.ObjectHelper;
import com.domain.plus.entity.PlusArticle;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/8 10:44
 * @Description: 文章管理
 */
@RestController
public class PlusArticleController extends BaseController {


    @Autowired
    private PlusArticleService plusArticleService;

    @Autowired
    private PageInfo pageInfo;

    /**
     *  文章查询
     * @return
     */
    @GetMapping("/admin/article/list")
    public ModelAndView articleQuery(Integer type){
        map.clear();
        int count = plusArticleService.countType(type);
        pageInfo.format(count, request);
        List<PlusArticle> list = plusArticleService.getType(type,pageInfo.getPageNumber(),pageInfo.getPageSize());
        map.put("list", list);
        map.put("pageInfo", pageInfo);
        map.put("type", type);
        return new ModelAndView("plus/article/articleList").addAllObjects(map);
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("/admin/article/insert")
    public ModelAndView articleInsert(PlusArticle plusArticle){
        if(ObjectHelper.getOrPost(request)){
            return new ModelAndView("plus/article/articleInsert");
        } else {
            boolean b = plusArticleService.notExist(plusArticle.getArticleType());
            if (b){
                plusArticle.setUserId(super.sessionAdminUser().getId());
                plusArticle.setCreateTime(System.currentTimeMillis());
                plusArticleService.insert(plusArticle);
                super.responseJson(JsonUtil.jsonForward("添加成功", "/admin/article/list"));
            }else {
                super.responseJson(JsonUtil.jsonError("请勿重复添加:"+ Consts.PlusArticleType.getType(plusArticle.getArticleType()), null));
            }
        }
        return null;
    }


    /**
     *  更新
     * @param plusArticle
     * @return
     */
    @RequestMapping("/admin/article/update")
    public ModelAndView articleUpdate(PlusArticle plusArticle) {
        if (ObjectHelper.getOrPost(request)) {
            return new ModelAndView("plus/article/articleUpdate").addObject("entity",plusArticleService.findById(plusArticle.getId()));
        } else {
            plusArticle.setUpdateTime(System.currentTimeMillis());
            plusArticleService.updateArticle(plusArticle);
            super.responseJson(JsonUtil.jsonForward("修改成功", "/admin/article/list"));
        }
        return null;
    }
}
