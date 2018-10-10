package com.domain.plus.vo;

import com.domain.plus.entity.OrderRenew;
import com.domain.plus.entity.PlusOrder;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/14 19:36
 * @Description:
 */
public class OrderVo extends PlusOrder {

    /**
     * 用户手机号
     */
    private String phone;


    /**
     * 城市
     */
    private String city;

    /**
     * 订单信息
     */
    private OrderRenew orderRenew;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public OrderRenew getOrderRenew() {
        return orderRenew;
    }

    public void setOrderRenew(OrderRenew orderRenew) {
        this.orderRenew = orderRenew;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
