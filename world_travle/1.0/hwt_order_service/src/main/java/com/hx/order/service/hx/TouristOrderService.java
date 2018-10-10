package com.hx.order.service.hx;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoUserInfo;
import com.hwt.domain.entity.order.vo.OrderInfoVO;

/**
 * 游客订单业务层
 * @author Administrator
 *
 */
public interface TouristOrderService {

	/**
	 * 通过条件查询订单
	 * @param map
	 * @return
	 */
	List<OrderInfoVO> qureyByMap(Map<String, Object> map);

	/**
	 * 申请退款
	 * @param user_id
	 * @param order_id
	 */
	void apply_refundable(Long user_id, Long order_id);

	/**
	 * 订单删除
	 * @param user_id
	 * @param order_id
	 */
	void delete(Long user_id, Long order_id);

	/**
	 * 获取导游的im_id
	 * @param cicerone_user_id
	 * @return
	 */
	HxCiceroneInfoUserInfo cicerone_im_id(Long cicerone_user_id);

	/**
	 * 通过订单id查询详情
	 * @param order_id
	 * @param user_id 
	 * @return
	 */
	OrderInfoVO query_info_by_order_id_user_id(Long order_id, Long user_id);

}
