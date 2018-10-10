package com.hwt.domain.entity.user.wallet.Vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="账单")
public class HxUserWalletBillVo implements Serializable {
	
	/**
	 * 账单id
	 */
	@ApiModelProperty(value="账单id")
	 private Long bill_id;
    /**
     * 描述
     */
	@ApiModelProperty(value="描述")
    private String bill_dec;

    /**
     * 说明
     */
	@ApiModelProperty(value="说明  标题")
    private String bill_title;

    /**
     * 1-充值  2-支付 3-收入 4-退款 5-提现  6-违约金收入
     */
	@ApiModelProperty(value="1-充值  2-支付 3-收入 4-退款 5-提现  6-违约金收入")
    private Integer bill_type;

    /**
     * 订单编号
     */
	@ApiModelProperty(value="订单编号")
    private String order_num;

    /**
     * 交易单号
     */
	@ApiModelProperty(value="订单编号")
    private String trans_num;
	

    /**
     * 操作金额
     */
	@ApiModelProperty(value="操作金额")
    private BigDecimal operation_amount;

	/**
     * 当时总余额
     */
	@ApiModelProperty(value="当时总余额")
    private BigDecimal balance;
    
    /**
     * 是否是钱包操作  1-否 2-是  默认1
     */
	@ApiModelProperty(value="是否是钱包操作  1-否 2-是  默认1")
    private Integer is_wallet;

    /**
     * 1-支付宝 2-微信 3-钱包 4-其他
     */
	@ApiModelProperty(value="1-支付宝 2-微信 3-钱包 4-其他")
    private Integer source;

    /**
     * 备注
     */
	@ApiModelProperty(value="备注")
    private String remarks;

    /**
     * 是否进账  0-否 1- 是
     */
	@ApiModelProperty(value="是否进账  0-否 1- 是")
    private Integer is_get;

    /**
     * 创建时间
     */
	@ApiModelProperty(value="创建时间")
    private Long create_time;

    /**
     * 开始时间
     */
	@ApiModelProperty(value="开始时间")
    private Long stare_time;

    /**
     * 结束时间
     */
	@ApiModelProperty(value="结束时间")
    private Long end_time;

    /**
     * 第三方支付流水号
     */
	@ApiModelProperty(value="第三方支付流水号")
    private String flow_num;

	 /**
     * 是否成功  0-否 1-是
     */
	@ApiModelProperty(value=" 是否成功  0-否 1-是")
    private Integer is_success;
    
    private static final long serialVersionUID = 1L;

	public String getBill_dec() {
		return bill_dec;
	}

	public void setBill_dec(String bill_dec) {
		this.bill_dec = bill_dec;
	}

	public String getBill_title() {
		return bill_title;
	}

	public void setBill_title(String bill_title) {
		this.bill_title = bill_title;
	}

	public Integer getBill_type() {
		return bill_type;
	}

	public void setBill_type(Integer bill_type) {
		this.bill_type = bill_type;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getTrans_num() {
		return trans_num;
	}

	public void setTrans_num(String trans_num) {
		this.trans_num = trans_num;
	}

	public Integer getIs_wallet() {
		return is_wallet;
	}

	public void setIs_wallet(Integer is_wallet) {
		this.is_wallet = is_wallet;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIs_get() {
		return is_get;
	}

	public void setIs_get(Integer is_get) {
		this.is_get = is_get;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Long getStare_time() {
		return stare_time;
	}

	public void setStare_time(Long stare_time) {
		this.stare_time = stare_time;
	}

	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	public String getFlow_num() {
		return flow_num;
	}

	public void setFlow_num(String flow_num) {
		this.flow_num = flow_num;
	}

	public Integer getIs_success() {
		return is_success;
	}

	public void setIs_success(Integer is_success) {
		this.is_success = is_success;
	}

	public Long getBill_id() {
		return bill_id;
	}

	public void setBill_id(Long bill_id) {
		this.bill_id = bill_id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getOperation_amount() {
		return operation_amount;
	}

	public void setOperation_amount(BigDecimal operation_amount) {
		this.operation_amount = operation_amount;
	}

	
}