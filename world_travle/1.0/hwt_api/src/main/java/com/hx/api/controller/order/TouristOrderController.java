//package com.hx.api.controller.order;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoUserInfo;
//import com.hwt.domain.entity.mg.scenic.vo.ScenicSpotVO;
//import com.hwt.domain.entity.order.vo.OrderInfoVO;
//import com.hwt.domain.entity.user.Vo.LoginVo;
//import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVo;
//import com.hx.api.base.ResultEntity;
//import com.hx.api.validate.ValidateParam;
//import com.hx.api.validate.ValidateParam.CheckedType;
//import com.hx.order.service.hx.TouristOrderService;
//import com.hx.user.utils.BaseUtil;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//
///**
// * 游客订单
// * @author Administrator
// *
// */
//@Api(value = "API - UserOrderController", description = "游客订单")
//@RestController
//public class TouristOrderController {
//	@Autowired
//	private TouristOrderService touristOrderService;
//	
//	
//    /**
//     * 游客查看订单
//     * @param startNum		起始条数  默认是0
//     * @param pageSize			展示条数  默认10条
//     * @param status			0-全部 1-待确认 2-待完成 3-待评价  默认0
//     * @return
//     */
//    @ApiOperation(value = "游客查看订单" , notes = "游客查看订单", response = OrderInfoVO.class)
//    @ApiImplicitParams({
//    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "startNum", value = "起始条数  默认是0", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "pageSize", value = "展示条数  默认10条", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "status", value = "0-全部 1-待确认 2-待完成 3-完成订单 4-退款 5-待评价   默认0", dataType = "string",required = true,paramType = "query"),
//    })
//    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
//	@PostMapping("/touristOrder/qurey")
//	public ResultEntity qurey(String token,@RequestParam(defaultValue="0")Integer startNum, @RequestParam(defaultValue="10")Integer pageSize, @RequestParam(defaultValue="0")Integer status){
//    	LoginVo loginVo = BaseUtil.getLoginVo(token);
//    	Map<String , Object> map = new HashMap<>();
//    	map.put("startNum", startNum);
//    	map.put("pageSize", pageSize);
//    	map.put("status", status);
//    	map.put("user_id", loginVo.getUser_id());
//		List<OrderInfoVO> list = touristOrderService.qureyByMap(map);
//		return new ResultEntity(list);
//		
//	}
//	
//   
//    /**
//     * 申请退款
//     * @param token		
//     * @param order_id			订单id
//     * @return
//     */
//    @ApiOperation(value = "申请退款" , notes = "申请退款", response = ResultEntity.class)
//    @ApiImplicitParams({
//    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
//    })
//    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
//	@PostMapping("/touristOrder/apply_refundable")
//    public ResultEntity apply_refundable(String token,Long order_id){
//    	LoginVo loginVo = BaseUtil.getLoginVo(token);
//    	touristOrderService.apply_refundable(loginVo.getUser_id(),order_id);
//    	return new ResultEntity("200","申请成功");
//    }
//    
//    /**
//     * 订单删除
//     */
//    @ApiOperation(value = "订单删除" , notes = "订单删除", response = ResultEntity.class)
//    @ApiImplicitParams({
//    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
//    })
//    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
//	@PostMapping("/touristOrder/delete")
//    public ResultEntity delete(String token,Long order_id){
//    	LoginVo loginVo = BaseUtil.getLoginVo(token);
//    	touristOrderService.delete(loginVo.getUser_id(),order_id);
//    	return new ResultEntity("200","删除成功");
//    }
//    
//    /**
//     * 获取导游的im_id
//     */
//    @ApiOperation(value = "获取导游的im_id" , notes = "获取导游的im_id", response = HxCiceroneInfoUserInfo.class)
//    @ApiImplicitParams({
//    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "cicerone_user_id", value = "导游的用户id", dataType = "string",required = true,paramType = "query"),
//    })
//    @ValidateParam(field={"token","cicerone_user_id"}, checkedType = CheckedType.TOKEN)
//	@PostMapping("/touristOrder/cicerone_im_id")
//    public ResultEntity cicerone_im_id(Long cicerone_user_id){
//    	HxCiceroneInfoUserInfo hxCiceroneInfoUserInfo = touristOrderService.cicerone_im_id(cicerone_user_id);
//    	return new ResultEntity(hxCiceroneInfoUserInfo);
//    }
//    
//    /**
//     * 根据订单id查询订单详情
//     */
//    @ApiOperation(value = "根据订单id查询订单详情" , notes = "根据订单id查询订单详情", response = OrderInfoVO.class)
//    @ApiImplicitParams({
//    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
//    })
//    @ValidateParam(field={"token","order_id"}, checkedType = CheckedType.TOKEN)
//    @PostMapping("/touristOrder/query_info")
//    public ResultEntity query_info(String token, Long order_id){
//    	LoginVo loginVo = BaseUtil.getLoginVo(token);
//    	OrderInfoVO orderInfoVO = touristOrderService.query_info_by_order_id_user_id(order_id,loginVo.getUser_id());
//    	return new ResultEntity(orderInfoVO);
//    }
//    
//    
//}
