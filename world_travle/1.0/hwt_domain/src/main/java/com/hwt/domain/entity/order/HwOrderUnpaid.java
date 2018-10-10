package com.hwt.domain.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="下单")
public class HwOrderUnpaid implements Serializable {
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
     * 买家留言
     */
    @ApiModelProperty(value = "买家留言")
    private String buyer_message;

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

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
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

    public Long getLine_id() {
        return line_id;
    }

    public void setLine_id(Long line_id) {
        this.line_id = line_id;
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

    public String getBuyer_message() {
        return buyer_message;
    }

    public void setBuyer_message(String buyer_message) {
        this.buyer_message = buyer_message;
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
}