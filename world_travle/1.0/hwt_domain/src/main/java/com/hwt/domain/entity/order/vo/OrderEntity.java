package com.hwt.domain.entity.order.vo;

import java.io.Serializable;

public class OrderEntity implements Serializable {
    //订单id
    private Integer order_id;
    //订单编号
    private String order_num;
    //订单名称(描述)
    private String dec;
    //支付方式 1-支付宝 2-微信 3- 余额  4-其他
    private String paymen_type;
    //订单来源  只有app来源
    private String orderSource;
    //订单状态
    private Integer status;
    //订单金额
    private Long payment;
    //下单时间
    private Long payment_time;
    //订单总条数
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public OrderEntity() {
    }

    public Integer getOrder_id() {

        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Long getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Long payment_time) {
        this.payment_time = payment_time;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getPaymen_type() {
        return paymen_type;
    }

    public void setPaymen_type(String paymen_type) {
        this.paymen_type = paymen_type;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }
}
