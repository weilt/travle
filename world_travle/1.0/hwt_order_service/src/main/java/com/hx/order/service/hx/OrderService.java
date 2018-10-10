package com.hx.order.service.hx;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderUnpaid;
import com.hwt.domain.entity.order.HwOrderUser;
import com.hwt.domain.entity.order.HxOrderInfo;
import com.hwt.domain.entity.order.vo.HwOrderUserInsert;
import com.hwt.domain.entity.order.vo.HxOrderInfoBaseVo;
import com.hwt.domain.entity.order.vo.OrderDetailsVo;
import com.hwt.domain.entity.order.vo.OrderInfoVO;

public interface OrderService {

	/**
	 * 下单
	 * @param hwOrder
	 * @param hwOrderUserInserts
	 */
	HwOrderUnpaid insert(HwOrderUnpaid hwOrder, String hwOrderUserInserts)  throws Exception ;

	/**
	 * 客户端查询订单
	 * @param map
	 * @return
	 */
	List<HxOrderInfoBaseVo> qureyByMapToHx(Map<String, Object> map) throws Exception ;

	/**
	 * 申请退款
	 * 
	 * @param user_id
	 * @param order_id
	 */
	void apply_refundable(Long user_id, Long order_id) throws Exception ;

	/**
	 * 游客删除订单
	 * @param user_id
	 * @param order_id
	 */
	void tourist_delete(Long user_id, Long order_id) throws Exception ;

	/**
	 * 导游订单删除
	 * @param user_id
	 * @param order_id
	 */
	void cicerone_delete(Long user_id, Long order_id) throws Exception ;

	/**
	 * 导游确认订单
	 * @param order_id
	 * @param user_id
	 */
	void confirm_order(Long order_id, Long user_id) throws Exception ;

	/**
	 * 导游确认退款
	 * @param order_id
	 * @param user_id
	 */
	void confirm_refundable(Long order_id, Long user_id) throws Exception ;

	 /**
     * 导游取消订单
     * 
     * 订单一旦开始就不能 导游只能开订单开始前取消订单，全额退款
     */
	void cicerone_cancel(Long user_id, Long order_id) throws Exception ;

	/**
	 * 导游拒绝订单
	 * @param user_id
	 * @param order_id
	 * @param type     0-自动拒绝   1-手动
	 */
	void cicerone_refuse(Long user_id, Long order_id, int type) throws Exception ;

	/**
	 * 查看订单详情
	 * @param user_id
	 * @param order_id 
	 * @return
	 */
	OrderDetailsVo qureyDetails(Long user_id, Long order_id) throws Exception ;

	/**
	 * 支付成功
	 * @param order_num
	 */
	void pay_success(String order_num) throws Exception ;


}
