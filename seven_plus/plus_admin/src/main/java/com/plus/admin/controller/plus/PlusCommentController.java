package com.plus.admin.controller.plus;

import com.admin.service.plus.PlusCommentService;
import com.common.pakag.PageInfo;
import com.common.util.JsonUtil;
import com.domain.plus.vo.CommentVo;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/13 10:10
 * @Description: 评论管理
 */
@RestController
public class PlusCommentController extends BaseController {

    @Autowired
    private PlusCommentService commentService;

    @Autowired
    private PageInfo pageInfo;

    /**
     * 评论列表
     * @param phone
     * @param storeName
     * @param storeAddress
     * @return
     */
    @GetMapping("/admin/comment/list")
    public ModelAndView commentList(String phone,String storeName,String storeAddress){
        super.map.clear();
        int count = commentService.countComment(phone,storeName,storeAddress);
        pageInfo.format(count,super.request);
        List<CommentVo> list = commentService.findComment(phone,storeName,storeAddress,pageInfo.getPageNumber(),pageInfo.getPageSize());
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("phone",phone);
        map.put("storeName",storeName);
        map.put("storeAddress",storeAddress);
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return new ModelAndView("plus/comment/commentList").addAllObjects(map);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/admin/comment/delete")
    public ModelAndView deleteComment(Long ... ids){
        int rowCunt = commentService.deleteComment(ids);
        if (rowCunt == 0){
            //删除失败
            super.responseJson(JsonUtil.jsonError("删除失败！"));
        } else {
            super.responseJson(JsonUtil.jsonRefresh("删除成功！"));
        }
        return null;
    }


    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/admin/comment/commentInfo")
    public ModelAndView commentInfo(Long id){
        map.clear();
        CommentVo commentVo = commentService.commentInfo(id);
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("entity",commentVo);
        return new ModelAndView("plus/comment/commentInfo").addAllObjects(map);
    }

}
