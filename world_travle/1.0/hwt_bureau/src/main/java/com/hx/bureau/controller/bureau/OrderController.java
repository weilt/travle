package com.hx.bureau.controller.bureau;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.order.vo.OrderDetailsVo;
import com.hx.bureau.base.ResultEntity;
import com.hx.bureau.util.BaseController;
import com.hx.bureau.validate.ValidateParam;
import com.hx.core.logs.annotation.Log;
import com.hx.core.logs.annotation.Log.LogType;
import com.hx.order.service.bureau.BureauOrderService;
import com.hx.order.service.bureau.vo.PageResultOrder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API - OrderController", description = "订单管理")
public class OrderController extends BaseController{
	@Autowired
	private BureauOrderService bureauOrderService;
	
	/**
	 * 跳转到订单管理界面
	 * @return
	 */
	@RequestMapping("bureau/order/list")
	public ModelAndView list() {
		return new ModelAndView("bureau/order/order-list");
	}
	
	
	/**
	 * 查询订单
	 * @param pageSize
	 * @param startNum
	 * @param orderBy
	 * @param status	0-全部   1-待确认  2-待开始  3-进行中  4-已完成 5-退款订单
	 * @param order_num
	 * @param filde
	 * @return
	 */
	@RequestMapping("bureau/order/query")
	public ResultEntity query(@RequestParam(defaultValue = "10")Integer pageSize, @RequestParam(defaultValue = "0")Integer startNum, String orderBy, @RequestParam(defaultValue = "0",name = "paramMap[status]")Integer status,
			@RequestParam(name = "paramMap[order_num]", required = false) String order_num,@RequestParam(name = "paramMap[filde]", required = false) String filde){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("order_num", order_num);
		map.put("filde", filde);
		map.put("status", status);
		map.put("bureau_id", sessionUser().getBureau_id());
		PageResultOrder pageResultOrder = bureauOrderService.query(map);
		return new ResultEntity(pageResultOrder);
	}
	
	/**
	 * 拒绝订单
	 */
	@Log(logType = LogType.OPERATION, dec = "拒绝订单")
	@ValidateParam(field={"order_id","business_remarks"}, message={"order_id不能为空","拒绝原因不能为空"})
	@PostMapping("bureau/order/refuse")
	public ResultEntity refuse(Long order_id,String business_remarks)  throws Exception {
		
		Long bureau_id = sessionUser().getBureau_id();
		bureauOrderService.refuse(order_id,bureau_id,business_remarks);
		return new ResultEntity("200","操作成功");
		
	}
	
	/**
	 * 取消订单
	 */
	@Log(logType = LogType.OPERATION, dec = "取消订单")
	@ValidateParam(field={"order_id","business_remarks"}, message={"order_id不能为空","拒绝原因不能为空"})
	@PostMapping("bureau/order/cancel")
	public ResultEntity cancel(Long order_id,String business_remarks)  throws Exception {
		
		Long bureau_id = sessionUser().getBureau_id();
		bureauOrderService.cancel(order_id,bureau_id,business_remarks);
		return new ResultEntity("200","操作成功");
		
	}
	
	/**
	 * 订单详情
	 */
	@ApiOperation(value = "订单详情" , notes = "订单详情", response = OrderDetailsVo.class)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string",required = true,paramType = "query"),
    })
	@ValidateParam(field={"order_id"}, message={"order_id不能为空"})
	@PostMapping("bureau/order/details")
	public ResultEntity details(Long order_id){
		Long bureau_id = sessionUser().getBureau_id();
    	OrderDetailsVo orderDetailsVo = bureauOrderService.qureyDetails(bureau_id,order_id);
		return new ResultEntity(orderDetailsVo);
		
	}
	
	/**
	 * 订单确认
	 */
	@Log(logType = LogType.OPERATION, dec = "订单确认")
	@ValidateParam(field={"order_id"}, message={"order_id不能为空"})
	@PostMapping("bureau/order/confirm")
	public ResultEntity confirm(Long order_id)  throws Exception {
		Long bureau_id = sessionUser().getBureau_id();
    	bureauOrderService.confirm(bureau_id,order_id);
		return new ResultEntity("200","操作成功");
		
	}
}
