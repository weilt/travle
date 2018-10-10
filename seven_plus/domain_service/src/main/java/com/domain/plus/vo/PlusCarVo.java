package com.domain.plus.vo;

import com.domain.plus.entity.PlusCar;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/9 19:11
 * @Description:
 */
public class PlusCarVo extends PlusCar {

    private String phone;

    private Integer orderType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
