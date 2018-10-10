package com.hwt.domain.entity.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="订单商品")
public class HxOrderInfoVo implements Serializable {
   
	/**
     * 订单id
     */
	@ApiModelProperty(value = "订单id")
    private Long order_id;

    /**
     * 单价  导游为每天   旅行社为每人
     */
	@ApiModelProperty(value = "单价  导游为每天   旅行社为每人")
    private BigDecimal unit_price;

    /**
     * 线路id  0为没有选择
     */
	@ApiModelProperty(value = "线路id  0为没有选择")
    private Long travel_line_id;

    /**
     * 封面图
     */
	@ApiModelProperty(value = "封面图")
    private String image;

    /**
     * 描述
     */
	@ApiModelProperty(value = "描述")
    private String dec;

    private static final long serialVersionUID = 1L;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public Long getTravel_line_id() {
        return travel_line_id;
    }

    public void setTravel_line_id(Long travel_line_id) {
        this.travel_line_id = travel_line_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}