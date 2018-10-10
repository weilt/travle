package com.plus.admin.controller.api;

import com.admin.service.api.ArticleService;
import com.admin.service.api.ImgService;
import com.common.consts.Consts;
import com.domain.plus.entity.PlusArticle;
import com.domain.plus.param.ImgParam;
import com.domain.plus.param.IndexParam;
import com.domain.plus.param.MoreServiceParam;
import com.plus.admin.controller.api.result.ResultCollection;
import com.plus.admin.controller.api.result.ResultEntity;
import com.plus.admin.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 09:17
 * @Description:
 */

@Api(value = "API - IndexController", description = "小程序首页")
@RestController
public class IndexController extends BaseApiController {


    @Autowired
    private ImgService imgService;

    @Autowired
    private ArticleService articleService;

    /**
     * 获取广告位
     * @return
     */
    @ApiOperation(value = "查询广告为" , notes = "查询广告为", response = ResultCollection.class)
    @ApiImplicitParams({
    })
    @PostMapping("/advertisement")
    public ResultCollection advertisement(){
        List<String> imgs = imgService.getAdvertisement(Consts.PlusImgType.IMG_TYPE_0.getCode());
        return ResultCollection.build(imgs);
    }


    /**
     * 产出描述
     * @param type 1：天天洗车 2：划痕无忧
     * @return
     */
    @ApiOperation(value = "查询产品宣传图及描述" , notes = "查询产品宣传图及描述", response = ResultEntity.class)
    @PostMapping("/product")
    public ResultEntity productDescription(@RequestBody IndexParam type) {
        String comment;
        String img;
        if (type.getType() == 1){
            comment = articleService.getComment(Consts.PlusArticleType.TYPE_2.getCode());
            img = imgService.getImg(Consts.PlusImgType.IMG_TYPE_2.getCode());
        } else {
            comment = articleService.getComment(Consts.PlusArticleType.TYPE_1.getCode());
            img = imgService.getImg(Consts.PlusImgType.IMG_TYPE_1.getCode());
        }
        Map<String,String> map = new HashMap<>();
        map.put("comment",comment);
        map.put("img",img);
        return ResultEntity.build(map);
    }


    /**
     * 查询会员协议
     * @return
     */
    @ApiOperation(value = "查询会员协议", notes = "查询会员协议",response = ResultEntity.class)
    @PostMapping("/vpiAgreement")
    public ResultEntity<String> vpiAgreement() {
        String comment = articleService.getComment(Consts.PlusArticleType.TYPE_3.getCode());
        return ResultEntity.build(comment);
    }


    /**
     * 车友
     * @return
     */
    @ApiOperation(value = "查询车友描述", notes = "查询车友描述",response = ResultEntity.class)
    @PostMapping("/catFriend")
    public ResultEntity<Map> catFriend() {
        String comment = articleService.getComment(Consts.PlusArticleType.TYPE_4.getCode());
        String img = imgService.getImg(Consts.PlusImgType.IMG_TYPE_3.getCode());
        Map<String,String> map = new HashMap<>();
        map.put("comment",comment);
        map.put("img",img);
        return ResultEntity.build(map);
    }


    /**
     * 获取示意图
     * @return
     */
    @ApiOperation(value = "获取示意图", notes = "获取示意图",response = ResultEntity.class)
    @PostMapping("/api/getExamples")
    public ResultEntity<String> getExamples(@RequestBody ImgParam param) {
        String img = imgService.getImg(param.getType());
        return ResultEntity.build(img);
    }

    /**
     * 更多服务
     * @return
     */
    @ApiOperation(value = "更多服务", notes = "moreService",response = PlusArticle.class)
    @PostMapping("/api/moreService")
    public ResultEntity<PlusArticle> moreService(@RequestBody MoreServiceParam param) {
        PlusArticle article = articleService.getArticleByType(param.getType());
        return ResultEntity.build(article);
    }



}
