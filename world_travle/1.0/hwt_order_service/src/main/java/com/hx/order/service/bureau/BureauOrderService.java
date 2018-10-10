package com.hx.order.service.bureau;

import java.util.Map;

import com.hwt.domain.entity.order.vo.OrderDetailsVo;
import com.hx.order.service.bureau.vo.PageResultOrder;

public interface BureauOrderService {

	/**
	 * 查询
	 * @param map
	 * @return
	 */
	PageResultOrder query(Map<String, Object> map);

	/**
	 * 拒绝
	 * @param order_id
	 * @param bureau_id
	 * @param business_remarks 
	 */
	void refuse(Long order_id, Long bureau_id, String business_remarks)  throws Exception ;

	/**
	 * 订单详情
	 * @param bureau_id
	 * @param order_id
	 * @return
	 */
	OrderDetailsVo qureyDetails(Long bureau_id, Long order_id);

	/**
	 * 取消订单
	 * @param order_id
	 * @param bureau_id
	 * @param business_remarks 
	 */
	void cancel(Long order_id, Long bureau_id, String business_remarks)  throws Exception ;

	/**
	 * 订单确认
	 * @param bureau_id
	 * @param order_id
	 */
	void confirm(Long bureau_id, Long order_id)  throws Exception ;

}
