package com.domain.plus.mapper;

import java.util.List;

import com.domain.plus.vo.OrderRenewVo;
import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.OrderRenew;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface OrderRenewMapper {

    int insertOrderRenew(OrderRenew object);

    int updateOrderRenew(OrderRenew object);

    int update(OrderRenew.UpdateBuilder object);

    List<OrderRenew> queryOrderRenew(OrderRenew object);

    OrderRenew queryOrderRenewLimit1(OrderRenew object);


    OrderRenew queryOrderRenewByOrder(@Param("orderId")Long orderId);

    /**
     * 获取订单支付
     * @param userId
     * @param orderType
     * @return
     */
    List<OrderRenewVo> queryOrderRenewByUserIdAndType(@Param("userId")Long userId, @Param("orderType")Integer orderType);

    /**
     * 通过订单号查询支付记录
     * @param orderNo
     * @return
     */
    OrderRenew queryOrderRenewByOrderNo(@Param("orderNo")String orderNo);

    /**
     * 获取总收益
     * @return
     */
    Long queryTotalMoney();

    /**
     * 通过orderId获取支付信息
     * @param orderId
     * @return
     */
    OrderRenew queryRenewByOrderId(@Param("orderId")Long orderId);

    /**
     * 通过订单Id删除
     * @param orderId
     * @return
     */
    int deleteByOrderId(@Param("orderId")Long orderId);
}