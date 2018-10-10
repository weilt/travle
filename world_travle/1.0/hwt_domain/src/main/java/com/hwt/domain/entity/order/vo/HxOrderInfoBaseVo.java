package com.hwt.domain.entity.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="订单列表")
public class HxOrderInfoBaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
	/**
     * 订单id
     */
	@ApiModelProperty(value = "订单id")
    private Long order_id;
	
	 /**
     * 旅行社id   0为没有选择
     */
	@ApiModelProperty(value = "旅行社id   0为没有选择",required=true )
    private Long bureau_id;

    /**
     * 导游id   0为没有选择
     */
	@ApiModelProperty(value = "导游id   0为没有选择" ,required=true)
    private Long cicerone_id;
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间" ,required=true)
	private Long start_time;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间" ,required=true)
	private Long end_time;
	/**
	 * 状态 0-代付款 1-待确认 2-待完成 3-已完成 4-退款 5-退款完成 6-拒绝
	 */
	@ApiModelProperty(value = "状态 0-代付款 1-待确认 2-待完成 3-已完成 4-退款 5-退款完成 6-拒绝 7-失败" ,required=true)
	private Integer status;

    /**
     * 下单人id
     */
	@ApiModelProperty(value = "下单人id" )
    private Long user_id;
	
	/**
     * 实付金额
     */
	@ApiModelProperty(value = "实付金额" )
    private BigDecimal payment;
	
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
	
	/**
	 * 是否评价 0-未评价 1-评价
	 */
	@ApiModelProperty(value = "是否评价 0-未评价 1-评价")
	private Integer buyer_rate;

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getBureau_id() {
		return bureau_id;
	}

	public void setBureau_id(Long bureau_id) {
		this.bureau_id = bureau_id;
	}

	public Long getCicerone_id() {
		return cicerone_id;
	}

	public void setCicerone_id(Long cicerone_id) {
		this.cicerone_id = cicerone_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
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

	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBuyer_rate() {
		return buyer_rate;
	}

	public void setBuyer_rate(Integer buyer_rate) {
		this.buyer_rate = buyer_rate;
	}
	
	
}