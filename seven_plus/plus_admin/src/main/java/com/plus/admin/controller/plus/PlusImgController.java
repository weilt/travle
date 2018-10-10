package com.plus.admin.controller.plus;

import com.admin.service.plus.PlusImgService;
import com.common.consts.Consts;
import com.common.fileUpload.OssUtil;
import com.common.util.JsonUtil;
import com.common.util.ObjectHelper;
import com.domain.plus.entity.PlusImg;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zhoudu
 * @Date: 2018/8/10 11:59
 * @Description:
 */
@RestController
public class PlusImgController extends BaseController {


    @Autowired
    private PlusImgService plusImgService;

    /**
     * 广告位
     * @return
     */
    @GetMapping("/admin/img/advert")
    public ModelAndView advertisement(){
        super.map.clear();
        List<PlusImg>  list = plusImgService.findByTypes(0);
        map.put("list",list);
        return new ModelAndView("plus/img/advertList").addAllObjects(map);
    }


    /**
     * 广告位添加
     * @param path
     * @return
     */
    @RequestMapping("/admin/img/advertInsert")
    public ModelAndView advertInsert(String path){
        if (ObjectHelper.getOrPost(super.request)){
            //GET
            return new ModelAndView("plus/img/advertInsert");
        }else {
            //insert
            if (plusImgService.restrict(0,3)){
                OssUtil.getOssUtil().deleteImg(path);
                super.responseJson(JsonUtil.jsonForward("添加失败，只能添加三张图片！", "/admin/img/advert"));
            }else {
                PlusImg plusImg = new PlusImg();
                plusImg.setCreateTime(System.currentTimeMillis());
                plusImg.setImgName("广告轮播图");
                plusImg.setImgUrl(path);
                plusImgService.save(plusImg);
                super.responseJson(JsonUtil.jsonForward("添加成功", "/admin/img/advert"));
            }
        }
        return null;
    }


    /**
     * 更新
     * @param id
     * @return
     */
    @RequestMapping("/admin/img/update")
    public ModelAndView advertUpdate(Long id,String path, Integer type){
        PlusImg plusImg = plusImgService.findById(id);
        if (ObjectHelper.getOrPost(super.request)){
            //GET
            super.map.clear();
            map.put("entity",plusImg);
            if (plusImg.getImgType() > 0){
                //跳转到示意图页面
                return new ModelAndView("plus/img/exampleUpdate").addAllObjects(map);
            } else {
                //跳转到广告位界面
                return new ModelAndView("plus/img/advertUpdate").addAllObjects(map);
            }
        } else {
            String url;
            plusImg.setUpdateTime(System.currentTimeMillis());
            plusImg.setImgUrl(path);
            if (plusImg.getImgType() > 0){
                plusImg.setImgType(type);
                url = "/admin/img/examples";
            } else {
                url = "/admin/img/advert";
            }
            plusImgService.update(plusImg);
            super.responseJson(JsonUtil.jsonForward("修改成功", url));
        }
        return null;

    }



    /**
     * 示例图
     * @return
     */
    @GetMapping("/admin/img/examples")
    public  ModelAndView examples(){
        super.map.clear();
        List<PlusImg>  list = plusImgService.findByTypes(1,2,3,4,5,6,7,8,9,10,11);
        map.put("list",list);
        return new ModelAndView("plus/img/examplesList").addAllObjects(map);
    }


    /**
     * 添加示意图
     * @return
     */
    @RequestMapping("/admin/img/exInset")
    public ModelAndView examplesInset(Integer type,String path){
        if (ObjectHelper.getOrPost(request)){
            //GET
            return new ModelAndView("plus/img/exampleInsert");
        } else {
            //POST
            if (plusImgService.restrict(type,1)){
                OssUtil.getOssUtil().deleteImg(path);
                super.responseJson(JsonUtil.jsonForward("添加失败，不能重复添加！", "/admin/img/examples"));
            } else {
                PlusImg plusImg = new PlusImg();
                plusImg.setImgUrl(path);
                plusImg.setImgName(Consts.PlusImgType.getImgType(type));
                plusImg.setImgType(type);
                plusImg.setCreateTime(System.currentTimeMillis());
                plusImgService.save(plusImg);
                super.responseJson(JsonUtil.jsonForward("添加成功！", "/admin/img/examples"));
            }
        }
        return null;
    }


    /**
     * 详情
     * @return
     */
    @GetMapping("/admin/img/imgInfo")
    public ModelAndView imgInfo(Long id){
        super.map.clear();
        PlusImg plusImg = plusImgService.findById(id);
        map.put("entity",plusImg);
        return new ModelAndView("plus/img/imginfo").addAllObjects(map);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/admin/img/imgDelete")
    public ModelAndView imgDelete(Long id) {
        PlusImg plusImg = plusImgService.findById(id);
        String url;
        if (plusImg.getImgType() > 0){
            url = "/admin/img/examples";
        } else {
            url = "/admin/img/advert";
        }
        plusImgService.delete(plusImg);
        super.responseJson(JsonUtil.jsonForward("删除成功！", url));
        return null;
    }
}
