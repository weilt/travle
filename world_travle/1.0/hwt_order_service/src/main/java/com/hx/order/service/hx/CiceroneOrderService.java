package com.hx.order.service.hx;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.order.vo.OrderInfoVO;

public interface CiceroneOrderService {

	/**
	 * 导游查看订单
	 * @param map
	 * @return
	 */
	List<OrderInfoVO> qureyByMap(Map<String, Object> map);

	/**
	 * 根据订单id 导游id 查询详情
	 * @param order_id
	 * @param cicerone_id
	 * @return
	 */
	OrderInfoVO query_info_by_order_id_cicerone_id(Long order_id, Long cicerone_id);
		
	/**
	 * 确认订单
	 * @param order_id
	 * @param cicerone_id
	 */
	void confirm_order(Long order_id, Long cicerone_id);

	/**
	 * 确认退款
	 * @param order_id
	 * @param cicerone_id
	 */
	void confirm_refundable(Long order_id, Long cicerone_id);

}
