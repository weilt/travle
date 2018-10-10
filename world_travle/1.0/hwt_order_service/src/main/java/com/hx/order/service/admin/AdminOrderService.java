package com.hx.order.service.admin;

import com.hwt.domain.entity.order.vo.OrderDetailsVo;
import com.hx.order.service.admin.vo.AdminPageOrder;

import java.text.ParseException;
import java.util.Map;


public interface AdminOrderService {

    /**
     * 查询全部订单
     */
//    List<OrderEntity> selectAll(Integer page);
//
//
//    List<OrderEntity> selectPaid(Integer status,Integer page);

    /**
     * 查询线路
     * @param map
     * @return
     */
    AdminPageOrder selectQuery(Map<String, Object> map) throws ParseException, Exception;

     /**
     * 查询导游
     * @param map
     * @return
     */
    AdminPageOrder selectGuideQuery(Map<String, Object> map) throws ParseException, Exception;


    //订单详情
    OrderDetailsVo selectQureyDetails(Long order_id);
}
