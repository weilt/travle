package com.hwt.domain.mapper.order;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Map;

@Mapper
public interface HxOrderMapper {
    /**
     * 查询所有 全部订单
     * @param
     * @return
     */
//    @Select("SELECT\n" +
//            "\ta.order_num,\n" +
//            "\ta.payment_time,\n" +
//            "\ta.paymen_type\n" +
//            "\ta.payment,\n" +
//            "\ta.`status`,\n" +
//            "\tb.`dec`,\n" +
//            "\ta.order_id,\n" +
//            "\tc.total\n" +
//            "\tFROM\n" +
//            "\t(SELECT COUNT(1) total FROM hw_order where STATUS IN (1,2,3,7)) c,\n" +
//            "\thw_order a,\n" +
//            "\thx_order_info b\n" +
//            "\tWHERE\n" +
//            "\ta.`status` IN (1,2,3,7)\n" +
//            "\tAND b.order_id = a.order_id LIMIT #{page},10")
//    List<OrderEntity> selectAll(@Param("page") Integer page);
//
//    List<OrderEntity> selectPaid(@Param("status")Integer status,@Param("page") Integer page);

    //查询路线全部订单
    @Select("select * from hw_order where status != 0 and status != 4 and status != 5 and status != 7 and cicerone_id =0  and `status` != 6")
    List<HwOrder> selectQuery();
    //查询导游全部订单
    @Select("select * from hw_order where status != 0 and status != 4 and status != 5 and status != 7 and cicerone_id !=0 and `status` != 6")
    List<HwOrder> selectGuideQuery();


    Long selectQueryCountByMap(Map<String, Object> map);
    Long selectGuideQueryCountByMap(Map<String, Object> map);

    List<Map<String, Object>> selectQueryByMap(Map<String, Object> map);
    List<Map<String, Object>> selectGuideQueryByMap(Map<String, Object> map);
    /**
     * 通过id查询
     * @param order_id
     * @return
     */
    @Select("select * from hw_order  where  order_id = #{order_id}")
    HwOrderVo selectByorder_id(@Param("order_id")Long order_id);

    /**
     * 通过订单id查询  查询订单详情
     * @param order_id
     * @return
     */
    @Select("select * from hx_order_info where order_id = #{order_id}")
    HxOrderInfoVo selectQueryByOrderInfo(@Param("order_id")Long order_id);
    /**
     * 通过订单id查询 查询订单描述
     * @param order_id
     * @return
     */
    @Select("select * from hw_order_refund where order_id = #{order_id}")
    HwOrderRefundVo selectQueryByOrderRefund(Long order_id);

    /**
     * 通过订单id查询用户信息以及保险信息
     * @param order_id
     * @return
     */
    List<HwOrderUserVo> selectQueryListByOrderUser(@Param("order_id")Long order_id);
}
