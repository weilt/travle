package com.hwt.domain.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class HwOrderRefund implements Serializable {
    /**
     * 订单id
     */
    private Long order_id;

    /**
     * 退款金额
     */
    private BigDecimal refund_sum;

    /**
     * 是否完成退款  0-否 1-是
     */
    private Integer is_complete;

    /**
     * 退款描述
     */
    private String refund_dec;

    /**
     * 用户备注
     */
    private String user_remarks;

    /**
     * 商家备注
     */
    private String business_remarks;

    private static final long serialVersionUID = 1L;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public BigDecimal getRefund_sum() {
        return refund_sum;
    }

    public void setRefund_sum(BigDecimal refund_sum) {
        this.refund_sum = refund_sum;
    }

    public Integer getIs_complete() {
        return is_complete;
    }

    public void setIs_complete(Integer is_complete) {
        this.is_complete = is_complete;
    }

    public String getRefund_dec() {
        return refund_dec;
    }

    public void setRefund_dec(String refund_dec) {
        this.refund_dec = refund_dec;
    }

    public String getUser_remarks() {
        return user_remarks;
    }

    public void setUser_remarks(String user_remarks) {
        this.user_remarks = user_remarks;
    }

    public String getBusiness_remarks() {
        return business_remarks;
    }

    public void setBusiness_remarks(String business_remarks) {
        this.business_remarks = business_remarks;
    }
}