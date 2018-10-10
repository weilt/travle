package com.hwt.domain.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="订单人员")
public class HwOrder implements Serializable {
    /**
     * 订单id
     */
	@ApiModelProperty(value = "订单id" ,hidden=true)
    private Long order_id;
	
	/**
	 * 订单编号
	 */
	@ApiModelProperty(value = "订单编号" ,hidden=true)
	private String order_num;

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
     * 线路id 没有 0 默认0
     */
	@ApiModelProperty(value = "线路id 没有 0 默认0" ,required=true)
    private Long line_id;


    /**
     * 下单人id
     */
	@ApiModelProperty(value = "下单人id" ,hidden=true)
    private Long user_id;

    /**
     * 保险id  0 为没有保险
     */
	@ApiModelProperty(value = "保险id  0 为没有保险" ,hidden=true)
    private Long insurance_id;

    /**
     * 优惠券id  0 为没有优惠券
     */
	@ApiModelProperty(value = " 优惠券id  0 为没有优惠券",required=true )
    private Long coupon_id;

    /**
     * 实付金额
     */
	@ApiModelProperty(value = "实付金额" ,hidden=true)
    private BigDecimal payment;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = " 创建时间" ,hidden=true)
    private Long create_time;

    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间" ,hidden=true)
    private Long update_time;

    /**
     * 支付方式 1-支付宝 2- 微信  3-其他
     */
	@ApiModelProperty(value = "支付方式 1-支付宝 2- 微信  3-零钱 4-其他"  ,hidden=true)
    private Integer paymen_type;

    /**
     * 付款时间
     */
	@ApiModelProperty(value = "付款时间" ,hidden=true)
    private Long payment_time;

    /**
     * 申请退款时间
     */
	@ApiModelProperty(value = "申请退款时间" ,hidden=true)
    private Long apply_sales_time;

    /**
     * 开始时间
     */
	@ApiModelProperty(value = "开始时间")
    private Long start_time;

    /**
     * 结束时间
     */
	@ApiModelProperty(value = "结束时间")
    private Long end_time;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间" ,hidden=true)
    private Long completion_time;

    /**
     * 退款时间
     */
    @ApiModelProperty(value = "退款时间" ,hidden=true)
    private Long refundable_time;

    /**
     * 买家留言
     */
    @ApiModelProperty(value = "买家留言")
    private String buyer_message;

    /**
     * 是否评价 0-未评价 1-评价
     */
    @ApiModelProperty(value = "是否评价 0-未评价 1-评价" ,hidden=true)
    private Integer buyer_rate;

    /**
     * 状态 0-代付款 1-待确认 2-待完成 3-已完成 4-退款 5-退款完成 6-拒绝
     */
    @ApiModelProperty(value = "状态 0-代付款 1-待确认 2-待完成 3-已完成 4-退款 5-退款完成 6-拒绝 7-失败" ,hidden=true)
    private Integer status;

    /**
     * 买家姓名
     */
    @ApiModelProperty(value = "买家姓名",required=true)
    private String buyer_name;

    /**
     * 买家电话
     */
    @ApiModelProperty(value = "买家电话",required=true)
    private String buyer_phone;

    /**
     * 交易单号
     */
    @ApiModelProperty(value = "交易单号" ,hidden=true)
    private String transaction_num;

    /**
     * 是否删除  0-否  1-是
     */
    @ApiModelProperty(value = "是否删除  0-否  1-是" ,hidden=true)
    private Integer tourist_is_delete;

    /**
     * 是否删除  0-否  1-是
     */
    @ApiModelProperty(value = "是否删除  0-否  1-是" ,hidden=true)
    private Integer cicerone_is_delete;

    /**
     * 拒绝时间
     */
    @ApiModelProperty(value = "拒绝时间" ,hidden=true)
    private Long refuse_time;

    /**
     * 接受时间
     */
    @ApiModelProperty(value = "接受时间" ,hidden=true)
    private Long accept_time;

    /**
     * 是否结算  0-否 1-是
     */
    @ApiModelProperty(value = "是否结算  0-否 1-是" ,hidden=true)
    private Integer is_settlement;

    /**
     * 成人人数 默认1
     */
    @ApiModelProperty(value = "成人人数 默认1" )
    private Integer adult_num;

    /**
     * 儿童人数 默认0
     */
    @ApiModelProperty(value = "儿童人数 默认0" )
    private Integer children_num;

    /**
     * 总人数  默认1
     */
    @ApiModelProperty(value = "总人数  默认1" )
    private Integer people_num;

    private static final long serialVersionUID = 1L;

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

    public Long getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(Long insurance_id) {
        this.insurance_id = insurance_id;
    }

    public Long getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Long coupon_id) {
        this.coupon_id = coupon_id;
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

    public Integer getTourist_is_delete() {
        return tourist_is_delete;
    }

    public void setTourist_is_delete(Integer tourist_is_delete) {
        this.tourist_is_delete = tourist_is_delete;
    }

    public Integer getCicerone_is_delete() {
        return cicerone_is_delete;
    }

    public void setCicerone_is_delete(Integer cicerone_is_delete) {
        this.cicerone_is_delete = cicerone_is_delete;
    }

    public Long getRefuse_time() {
        return refuse_time;
    }

    public void setRefuse_time(Long refuse_time) {
        this.refuse_time = refuse_time;
    }

    public Long getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(Long accept_time) {
        this.accept_time = accept_time;
    }

    public Integer getIs_settlement() {
        return is_settlement;
    }

    public void setIs_settlement(Integer is_settlement) {
        this.is_settlement = is_settlement;
    }

    public Integer getAdult_num() {
        return adult_num;
    }

    public void setAdult_num(Integer adult_num) {
        this.adult_num = adult_num;
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

	public Long getLine_id() {
		return line_id;
	}

	public void setLine_id(Long line_id) {
		this.line_id = line_id;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
    
    
}