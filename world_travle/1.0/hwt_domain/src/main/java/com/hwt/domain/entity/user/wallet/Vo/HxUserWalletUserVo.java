package com.hwt.domain.entity.user.wallet.Vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="用户钱包")
public class HxUserWalletUserVo implements Serializable {
    /**
     * 用户id
     */
	@ApiModelProperty(value="用户id")
    private Long user_id;

    /**
     * 总余额
     */
	@ApiModelProperty(value="总余额")
    private BigDecimal balance;

    /**
     * 可使用金额
     */
	@ApiModelProperty(value=" 可使用金额")
    private BigDecimal is_can;

    /**
     * 不可使用金额
     */
	@ApiModelProperty(value="不可使用金额")
    private BigDecimal is_not_can;
	
	/**
	 * 是否设置密码  0-没有  1-有
	 */
	@ApiModelProperty(value="是否设置密码  0-没有  1-有")
	private Integer is_password;

    private static final long serialVersionUID = 1L;



	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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

	public Integer getIs_password() {
		return is_password;
	}

	public void setIs_password(Integer is_password) {
		this.is_password = is_password;
	}
	
}