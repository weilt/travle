package com.plus.admin.controller.api;

import com.admin.service.api.ArticleService;
import com.admin.service.api.UserService;
import com.common.consts.Consts;
import com.common.consts.UserContext;
import com.domain.plus.param.IndexParam;
import com.domain.plus.vo.UserAuthVo;
import com.plus.admin.controller.api.result.ResultEntity;
import com.plus.admin.controller.base.BaseApiController;
import io.swagger.annotations.Api;
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
 * @Date: 2018/8/22 14:55
 * @Description:
 */
@Api(value = "API - UserController", description = "用户相关")
@RestController
public class UserController extends BaseApiController {


    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取用户办业务信息" , notes = "getUserAuth", response = UserAuthVo.class)
    @PostMapping("/getUserAuth")
    public ResultEntity getUserAuth(){
        UserContext context = getUserContext();
        UserAuthVo result = userService.getUserAuth(context.getId());
        result.setId(context.getId());
        return ResultEntity.build(result);
    }

    @ApiOperation(value = "我的会员" , notes = "myVip", response = ResultEntity.class)
    @PostMapping("/api/myVip")
    public ResultEntity<Map> myVip(@RequestBody IndexParam type) {
        UserContext context = getUserContext();
        String comment;
        if (type.getType() == 1){
            comment = articleService.getComment(Consts.PlusArticleType.TYPE_2.getCode());
        } else {
            comment = articleService.getComment(Consts.PlusArticleType.TYPE_1.getCode());
        }
        List<Map<String,Object>> order = userService.findOrderByUserId(context.getId(),type.getType());
        Map<String,Object> map = new HashMap<>();
        map.put("comment",comment);
        map.put("order",order);
        return ResultEntity.build(map);
    }
}
