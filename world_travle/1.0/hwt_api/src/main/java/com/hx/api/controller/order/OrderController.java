package com.hx.api.controller.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderUnpaid;
import com.hwt.domain.entity.order.HwOrderUser;
import com.hwt.domain.entity.order.HxOrderInfo;
import com.hwt.domain.entity.order.vo.HwOrderUserInsert;
import com.hwt.domain.entity.order.vo.HxOrderInfoBaseVo;
import com.hwt.domain.entity.order.vo.OrderDetailsVo;
import com.hwt.domain.entity.order.vo.OrderInfoVO;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.core.exception.BaseException;
import com.hx.core.redis.RedisCache;
import com.hx.core.wyim.entity.emu.SMSType;
import com.hx.order.service.hx.OrderService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 订单
 * @author Administrator
 *
 */
@Api(value = "API - OrderController", description = "订单")
@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	/**
	 * 下单
	 */
	@ApiOperation(value = "下单" , notes = "下单", response = HwOrder.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "smsVerify", value = "验证码", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/order/insert")
	public ResultEntity insert(String token, HwOrderUnpaid hwOrder, String  hwOrderUserInserts, String smsVerify)  throws Exception  {
		
		String code = RedisCache.get(SMSType.order.smsTypePrefix + hwOrder.getBuyer_phone());
		
		if(StringUtils.isBlank(code) || !code.equals(smsVerify)) {
			throw new BaseException("验证码输入错误!");
		}
		
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		hwOrder.setUser_id(loginVo.getUser_id());
		HwOrderUnpaid object = orderService.insert(hwOrder,hwOrderUserInserts);
		return new ResultEntity(object);
		
	}
	
	 /**
     * 查看订单列表
     * @param startNum		起始条数  默认是0
     * @param pageSize			展示条数  默认10条
     * @param status			-1-全部 1-待确认 2-待完成 3-待评价  默认-1
     * @return
     */
    @ApiOperation(value = "查看订单列表" , notes = "查看订单列表", response = HxOrderInfoBaseVo.class)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "页码  默认是1", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "展示条数  默认10条", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "status", value = "-1-全部 1-待确认 2-待完成 3-完成订单 4-退款 5-待评价   默认-1", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/order/qurey")
	public ResultEntity qurey(String token,@RequestParam(defaultValue="1")Integer pageIndex, @RequestParam(defaultValue="10")Integer pageSize, @RequestParam(defaultValue="0")Integer status) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	Map<String , Object> map = new HashMap<>();
    	map.put("startNum", (pageIndex <= 1 ? 0 : pageIndex  - 1) * pageSize);
    	map.put("pageSize", pageSize);
    	map.put("status", status);
    	map.put("user_id", loginVo.getUser_id());
    	map.put("time", System.currentTimeMillis());
		List<HxOrderInfoBaseVo> list = orderService.qureyByMapToHx(map);
		return new ResultEntity(list);
		
	}
    
    /**
     * 订单详情
     * 
     */
    @ApiOperation(value = "订单详情" , notes = "订单详情", response = OrderDetailsVo.class)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    		@ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","order_id"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/order/details")
	public ResultEntity details(String token,Long order_id) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	
    	OrderDetailsVo orderDetailsVo = orderService.qureyDetails(loginVo.getUser_id(),order_id);
		return new ResultEntity(orderDetailsVo);
		
	}
    
    /**
     * 申请退款
     * @param token		
     * @param order_id			订单id
     * @return
     */
    @ApiOperation(value = "申请退款" , notes = "申请退款", response = ResultEntity.class)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/order/apply_refundable")
    public ResultEntity apply_refundable(String token,Long order_id) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	orderService.apply_refundable(loginVo.getUser_id(),order_id);
    	return new ResultEntity();
    }
    
    /**
     * 游客订单删除
     */
    @ApiOperation(value = "游客订单删除" , notes = "游客订单删除", response = ResultEntity.class)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/order/tourist_delete")
    public ResultEntity delete(String token,Long order_id) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	orderService.tourist_delete(loginVo.getUser_id(),order_id);
    	return new ResultEntity();
    }
    
    /**
     * 导游订单删除
     */
    @ApiOperation(value = "导游订单删除" , notes = "导游订单删除", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
    @PostMapping("/order/cicerone_delete")
    public ResultEntity cicerone_delete(String token,Long order_id) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	orderService.cicerone_delete(loginVo.getUser_id(),order_id);
    	return new ResultEntity();
    }
    
    /**
     * 导游确认订单
     */
    @ApiOperation(value = "导游确认订单" , notes = "导游确认订单", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
        @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","order_id"}, checkedType = CheckedType.TOKEN)
    @PostMapping("/order/cicerone_confirm_order")
    public ResultEntity cicerone_confirm_order(Long order_id,String token) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	orderService.confirm_order(order_id,loginVo.getUser_id());
    	return new ResultEntity();
    }
	
    /**
     * 导游确认退款
     */
    @ApiOperation(value = "导游确认退款" , notes = "导游确认退款", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
        @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","order_id","cicerone_id"}, checkedType = CheckedType.TOKEN)
    @PostMapping("/order/cicerone_confirm_refundable")
    public ResultEntity cicerone_confirm_refundable(Long order_id,String token) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	orderService.confirm_refundable(order_id,loginVo.getUser_id());
    	return new ResultEntity();
    }
    
    /**
     * 导游取消订单
     */
    @ApiOperation(value = "导游取消订单" , notes = "导游取消订单", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
    @PostMapping("/order/cicerone_cancel")
    public ResultEntity cicerone_cancel(String token,Long order_id) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	orderService.cicerone_cancel(loginVo.getUser_id(),order_id);
    	return new ResultEntity();
    }
    
    /**
     * 导游拒绝订单
     */
    @ApiOperation(value = "导游拒绝订单" , notes = "导游拒绝单", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
    @PostMapping("/order/cicerone_refuse")
    public ResultEntity cicerone_refuse(String token,Long order_id) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	orderService.cicerone_refuse(loginVo.getUser_id(),order_id,1);
    	return new ResultEntity();
    }
    
    
}
