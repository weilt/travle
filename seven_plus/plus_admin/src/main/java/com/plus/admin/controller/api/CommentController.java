package com.plus.admin.controller.api;

import com.admin.service.api.CommentService;
import com.common.consts.UserContext;
import com.domain.plus.param.AddCommentParam;
import com.domain.plus.param.CommentParam;
import com.domain.plus.vo.CommentStoreVo;
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
 * @Date: 2018/8/22 16:49
 * @Description:
 */
@Api(value = "API - CommentController", description = "评价相关")
@RestController
public class CommentController extends BaseApiController {


    @Autowired
    private CommentService commentService;


    @ApiOperation(value = "获取网点评价" , notes = "getStoreComment", response = CommentStoreVo.class)
    @PostMapping("/api/getStoreComment")
    public ResultEntity<Map<String,Object>> getStoreComment(@RequestBody CommentParam param){
        List<CommentStoreVo> list = commentService.getStoreComment(param.getStoreId(),param.getType(),param.getPageNo(),param.getPageSize());
        Boolean b = commentService.commentUnique(param.getRecordId());
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("isComment",b);
        return ResultEntity.build(map);
    }


    @ApiOperation(value = "网点评价" , notes = "storeComment", response = ResultEntity.class)
    @PostMapping("/api/storeComment")
    public ResultEntity storeComment(@RequestBody AddCommentParam pa){
        UserContext context = getUserContext();
        commentService.addStoreComment(context.getId(),pa);
        return ResultEntity.build();
    }

}
