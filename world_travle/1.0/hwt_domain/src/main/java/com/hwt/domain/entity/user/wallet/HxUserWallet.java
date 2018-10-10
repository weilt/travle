package com.hwt.domain.entity.user.wallet;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class HxUserWallet implements Serializable {
	
	 /**
     * 用户或者旅行社id
     */
    private Long name_id;

    /**
     * 1-用户钱包 2-旅行社钱包
     */
    private Integer name_type;

	 /**
     * 总余额
     */
    private BigDecimal balance;

    /**
     * 可使用金额
     */
    private BigDecimal is_can;

    /**
     * 不可使用金额
     */
    private BigDecimal is_not_can;

    /**
     * 违约金额
     */
    private BigDecimal bad_payment;

    /**
     * 已提现金额
     */
    private BigDecimal get_payment;

    /**
     * 销售总额
     */
    private BigDecimal sale_payment;

    /**
     * 支付密码
     */
    private String payment_password;

    /**
     * 支付秘钥
     */
    private String payment_salt;

    /**
     * 当天密码错误次数
     */
    private Integer day_pass_num;

    /**
     * 创建时间
     */
    private Long create_time;

    private static final long serialVersionUID = 1L;

	public Long getName_id() {
		return name_id;
	}

	public void setName_id(Long name_id) {
		this.name_id = name_id;
	}

	public Integer getName_type() {
		return name_type;
	}

	public void setName_type(Integer name_type) {
		this.name_type = name_type;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getIs_can() {
		return is_can;
	}

	public void setIs_can(BigDecimal is_can) {
		this.is_can = is_can;
	}

	public BigDecimal getIs_not_can() {
		return is_not_can;
	}

	public void setIs_not_can(BigDecimal is_not_can) {
		this.is_not_can = is_not_can;
	}

	public BigDecimal getBad_payment() {
		return bad_payment;
	}

	public void setBad_payment(BigDecimal bad_payment) {
		this.bad_payment = bad_payment;
	}

	public BigDecimal getGet_payment() {
		return get_payment;
	}

	public void setGet_payment(BigDecimal get_payment) {
		this.get_payment = get_payment;
	}

	public BigDecimal getSale_payment() {
		return sale_payment;
	}

	public void setSale_payment(BigDecimal sale_payment) {
		this.sale_payment = sale_payment;
	}

	public String getPayment_password() {
		return payment_password;
	}

	public void setPayment_password(String payment_password) {
		this.payment_password = payment_password;
	}

	public String getPayment_salt() {
		return payment_salt;
	}

	public void setPayment_salt(String payment_salt) {
		this.payment_salt = payment_salt;
	}

	public Integer getDay_pass_num() {
		return day_pass_num;
	}

	public void setDay_pass_num(Integer day_pass_num) {
		this.day_pass_num = day_pass_num;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

    
}