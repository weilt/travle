package com.hx.api.controller.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.order.HwOrderComment;
import com.hwt.domain.entity.order.vo.HwOrderCommentListVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.order.service.hx.OrderCommentService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API - OrderCommentController", description = "订单评论")
public class OrderCommentController {
	
	@Autowired
	private OrderCommentService orderCommentService;
	
	
    /**
     * 评论
     * @param token
     * @param order_id				订单id
     * @param parent_comment_id		恢复的哪一条  0不是回复
     * @param parent_user_id		回复的谁 0-不是回复的谁 
     * @param comment_dec			评价描述
     * @param image					图片组 最多6张
     * @param match_score			相符度  1-1星 2-2星 3-3星 4-4星 5-5星  线路的订单必填 
     * @param trip_score			行程安排  1-1星 2-2星 3-3星 4-4星 5-5星  线路的订单必填 
     * @param service_score			服务  1-1星 2-2星 3-3星 4-4星 5-5星    线路的订单必填 
     * @param score					综合评分  1-1星 2-2星 3-3星 4-4星 5-5星    导游的订单必填
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "评论" , notes = "评论", response = HwOrderComment.class)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "parent_comment_id", value = "恢复的哪一条  0不是回复", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "parent_user_id", value = "回复的谁 0-不是回复的谁 ", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "comment_dec", value = "评价描述", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "image", value = "图片组 最多6张", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "match_score", value = "相符度  1-1星 2-2星 3-3星 4-4星 5-5星  线路的订单必填 ", dataType = "string",required = false,paramType = "query"),
    		@ApiImplicitParam(name = "trip_score", value = "行程安排  1-1星 2-2星 3-3星 4-4星 5-5星  线路的订单必填 ", dataType = "string",required = false,paramType = "query"),
    		@ApiImplicitParam(name = "service_score", value = "服务  1-1星 2-2星 3-3星 4-4星 5-5星    线路的订单必填 ", dataType = "string",required = false,paramType = "query"),
    		@ApiImplicitParam(name = "score", value = "综合评分  1-1星 2-2星 3-3星 4-4星 5-5星    导游的订单必填", dataType = "string",required = false,paramType = "query"),
    		
    })
    @ValidateParam(field={"token","order_id","comment_dec"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/orderComment/insert")
	public ResultEntity insert(String token,Long order_id,@RequestParam(defaultValue="0")Long parent_comment_id,@RequestParam(defaultValue="0")Long parent_user_id,String comment_dec,String image
			, Integer match_score, Integer trip_score, Integer service_score, Float score ) throws Exception {
    	
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	HwOrderComment hwOrderComment = orderCommentService.insert(loginVo.getUser_id(),order_id,parent_comment_id,parent_user_id,comment_dec,image
    			,match_score,trip_score,service_score,score);
		return new ResultEntity(hwOrderComment);
	}
    
    
    /**
     * 评论查询
     */
    @ApiOperation(value = "评论查询" , notes = "评论查询", response = HwOrderCommentListVo.class)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "name_id", value = "导游id 或者线路id", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "name_type", value = "2-导游 3-线路", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "other_condition", value = "其他条件 0-全部 1-好评 2-有图 3-中评 4-差评", dataType = "string",paramType = "query"),
    		@ApiImplicitParam(name = "last_order_comment_id", value = "查询的最后一条评论id  默认0", dataType = "string", paramType = "query"),
    		@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string", paramType = "query")
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/orderComment/query")
    public ResultEntity query(Long name_id, Integer name_type, Long last_order_comment_id, Integer pageSize, Integer other_condition) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("name_id", name_id);
		map.put("name_type", name_type);
		map.put("other_condition", other_condition);
		map.put("last_order_comment_id", last_order_comment_id);
		map.put("pageSize", pageSize);
		HwOrderCommentListVo hwOrderCommentListVo = orderCommentService.query(map);
		return new ResultEntity(hwOrderCommentListVo);
	}
}
