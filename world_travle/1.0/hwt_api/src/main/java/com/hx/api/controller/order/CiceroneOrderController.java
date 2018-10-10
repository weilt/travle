//package com.hx.api.controller.order;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hwt.domain.entity.order.vo.OrderInfoVO;
//import com.hwt.domain.entity.user.Vo.LoginVo;
//import com.hx.api.base.ResultEntity;
//import com.hx.api.validate.ValidateParam;
//import com.hx.api.validate.ValidateParam.CheckedType;
//import com.hx.order.service.hx.CiceroneOrderService;
//import com.hx.user.utils.BaseUtil;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//
///**
// * 订单----导游方
// * @author Administrator
// *
// */
//@Api(value = "API - CiceroneOrderController", description = "订单----导游方")
//@RestController
//public class CiceroneOrderController {
//	@Autowired
//	private CiceroneOrderService ciceroneOrderService;
//	
//	/**
//	 * 导游查看订单
//	 */
//	 @ApiOperation(value = "导游查看订单" , notes = "导游查看订单", response = OrderInfoVO.class)
//	    @ApiImplicitParams({
//	    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//	            @ApiImplicitParam(name = "startNum", value = "起始条数  默认是0", dataType = "string",required = true,paramType = "query"),
//	            @ApiImplicitParam(name = "pageSize", value = "展示条数  默认10条", dataType = "string",required = true,paramType = "query"),
//	            @ApiImplicitParam(name = "status", value = "0-全部 1-待确认 2-待完成   6-待结算  默认0", dataType = "string",required = true,paramType = "query"),
//	    })
//	    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
//		@PostMapping("/ciceroneOrder/qurey")
//		public ResultEntity qurey(String token,@RequestParam(defaultValue="0")Integer startNum, 
//				@RequestParam(defaultValue="10")Integer pageSize, @RequestParam(defaultValue="0")Integer status){
//		 
//		 	LoginVo loginVo = BaseUtil.getLoginVo(token);
//	    	Map<String , Object> map = new HashMap<>();
//	    	map.put("startNum", startNum);
//	    	map.put("pageSize", pageSize);
//	    	map.put("status", status);
//	    	map.put("cicerone_id", loginVo.getUser_id());
//			List<OrderInfoVO> list = ciceroneOrderService.qureyByMap(map);
//			return new ResultEntity(list);
//			
//		}
//	 
//		/**
//	     * 导游根据订单id查询订单详情
//	     */
//	    @ApiOperation(value = "导游根据订单id查询订单详情" , notes = "导游根据订单id查询订单详情", response = OrderInfoVO.class)
//	    @ApiImplicitParams({
//	    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//	            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
//	    })
//	    @ValidateParam(field={"token","order_id"}, checkedType = CheckedType.TOKEN)
//	    @PostMapping("/ciceroneOrder/query_info")
//	    public ResultEntity query_info(Long order_id,String token){
//	    	LoginVo loginVo = BaseUtil.getLoginVo(token);
//	    	OrderInfoVO orderInfoVO = ciceroneOrderService.query_info_by_order_id_cicerone_id(order_id,loginVo.getUser_id());
//	    	return new ResultEntity(orderInfoVO);
//	    }
//	    
//	    /**
//	     * 确认订单
//	     */
//	    @ApiOperation(value = "确认订单" , notes = "确认订单", response = ResultEntity.class)
//	    @ApiImplicitParams({
//	    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
//	    })
//	    @ValidateParam(field={"token","order_id"}, checkedType = CheckedType.TOKEN)
//	    @PostMapping("/ciceroneOrder/confirm_order")
//	    public ResultEntity confirm_order(Long order_id,String token){
//	    	LoginVo loginVo = BaseUtil.getLoginVo(token);
//	    	ciceroneOrderService.confirm_order(order_id,loginVo.getUser_id());
//	    	return new ResultEntity();
//	    }
//	    
//	    /**
//	     * 确认退款
//	     */
//	    @ApiOperation(value = "确认退款" , notes = "确认退款", response = ResultEntity.class)
//	    @ApiImplicitParams({
//	    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "cicerone_id", value = "导游id", dataType = "string",required = true,paramType = "query"),
//	    })
//	    @ValidateParam(field={"token","order_id","cicerone_id"}, checkedType = CheckedType.TOKEN)
//	    @PostMapping("/ciceroneOrder/confirm_refundable")
//	    public ResultEntity confirm_refundable(Long order_id,Long cicerone_id){
//	    	ciceroneOrderService.confirm_refundable(order_id,cicerone_id);
//	    	return new ResultEntity();
//	    }
//}
