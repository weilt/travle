package com.hwt.domain.entity.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单返回对象
 * @author Administrator
 *
 */
@ApiModel(value="订单返回对象")
public class OrderInfoVO implements Serializable{
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
	@ApiModelProperty(value = " 旅行社id   0为没有选择")
    private Long bureau_id;

    /**
     * 导游id   0为没有选择
     */
	@ApiModelProperty(value = "导游id   0为没有选择")
    private Long cicerone_id;

    /**
     * 下单人id
     */
	@ApiModelProperty(value = "下单人id")
    private Long user_id;

    /**
     * 实付金额
     */
	@ApiModelProperty(value = " 实付金额")
    private BigDecimal payment;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
    private Long create_time;

    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间")
    private Long update_time;

    /**
     * 支付方式 1-支付宝 2- 微信  3-零钱 4-其他
     */
	@ApiModelProperty(value = "支付方式 1-支付宝 2- 微信  3-零钱 4-其他")
    private Integer paymen_type;

    /**
     * 付款时间
     */
	@ApiModelProperty(value = "付款时间")
    private Long payment_time;

    /**
     * 申请退款时间
     */
	@ApiModelProperty(value = "申请退款时间")
    private Long apply_sales_time;

    /**
     * 开始时间
     */
	@ApiModelProperty(value = "开始时间")
    private Long start_time;

    /**
     * 结束时间
     */
	@ApiModelProperty(value = " 结束时间")
    private Long end_time;

    /**
     * 完成时间
     */
	@ApiModelProperty(value = "完成时间")
    private Long completion_time;

    /**
     * 退款时间
     */
	@ApiModelProperty(value = " 退款时间")
    private Long refundable_time;

    /**
     * 买家留言
     */
	@ApiModelProperty(value = "买家留言")
    private String buyer_message;

    /**
     * 是否评价 0-未评价 1-评价
     */
	@ApiModelProperty(value = "是否评价 0-未评价 1-评价")
    private Integer buyer_rate;

    /**
     * 状态 0-代付款 1-待确认 2-待完成 3-已完成 4-退款 5-退款完成 6-拒绝
     */
	@ApiModelProperty(value = "状态 0-代付款 1-待确认 2-待完成 3-已完成 4-退款 5-退款完成 6-拒绝 7-失败")
    private Integer status;

    /**
     * 买家姓名
     */
	@ApiModelProperty(value = "买家姓名")
    private String buyer_name;

    /**
     * 买家电话
     */
	@ApiModelProperty(value = "买家电话")
    private String buyer_phone;

    /**
     * 交易单号
     */
	@ApiModelProperty(value = "交易单号")
    private String transaction_num;
    
    /**
     * 描述
     */
	@ApiModelProperty(value = "描述")
    private String dec;

    /**
     * 成人人数
     */
	@ApiModelProperty(value = "成人人数")
    private Integer adult_num;

    /**
     * 每日价格
     */
	@ApiModelProperty(value = "每日价格")
    private BigDecimal everyday_price;

    /**
     * 单价
     */
	@ApiModelProperty(value = " 单价")
    private BigDecimal unit_price;

    /**
     * 总价
     */
	@ApiModelProperty(value = " 总价")
    private BigDecimal total_fee;

    /**
     * 儿童人数
     */
	@ApiModelProperty(value = "儿童人数")
    private Integer children_num;

    /**
     *  总人数
     */
	@ApiModelProperty(value = "总人数")
    private Integer people_num;

    /**
     * 线路id  0为没有选择
     */
	@ApiModelProperty(value = "线路id  0为没有选择")
    private Long travel_line_id;

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

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Long update_time) {
		this.update_time = update_time;
	}

	public Integer getPaymen_type() {
		return paymen_type;
	}

	public void setPaymen_type(Integer paymen_type) {
		this.paymen_type = paymen_type;
	}

	public Long getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(Long payment_time) {
		this.payment_time = payment_time;
	}

	public Long getApply_sales_time() {
		return apply_sales_time;
	}

	public void setApply_sales_time(Long apply_sales_time) {
		this.apply_sales_time = apply_sales_time;
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

	public Long getCompletion_time() {
		return completion_time;
	}

	public void setCompletion_time(Long completion_time) {
		this.completion_time = completion_time;
	}

	public Long getRefundable_time() {
		return refundable_time;
	}

	public void setRefundable_time(Long refundable_time) {
		this.refundable_time = refundable_time;
	}

	public String getBuyer_message() {
		return buyer_message;
	}

	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}

	public Integer getBuyer_rate() {
		return buyer_rate;
	}

	public void setBuyer_rate(Integer buyer_rate) {
		this.buyer_rate = buyer_rate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getBuyer_phone() {
		return buyer_phone;
	}

	public void setBuyer_phone(String buyer_phone) {
		this.buyer_phone = buyer_phone;
	}

	public String getTransaction_num() {
		return transaction_num;
	}

	public void setTransaction_num(String transaction_num) {
		this.transaction_num = transaction_num;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public Integer getAdult_num() {
		return adult_num;
	}

	public void setAdult_num(Integer adult_num) {
		this.adult_num = adult_num;
	}

	public BigDecimal getEveryday_price() {
		return everyday_price;
	}

	public void setEveryday_price(BigDecimal everyday_price) {
		this.everyday_price = everyday_price;
	}

	public BigDecimal getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getChildren_num() {
		return children_num;
	}

	public void setChildren_num(Integer children_num) {
		this.children_num = children_num;
	}

	public Integer getPeople_num() {
		return people_num;
	}

	public void setPeople_num(Integer people_num) {
		this.people_num = people_num;
	}

	public Long getTravel_line_id() {
		return travel_line_id;
	}

	public void setTravel_line_id(Long travel_line_id) {
		this.travel_line_id = travel_line_id;
	}
    
    
}
