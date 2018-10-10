package com.hwt.domain.entity.user.wallet.Vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="银行卡")
public class HxUserWalletBankCardUserVo implements Serializable {
    /**
     * id
     */
	@ApiModelProperty(value="id")
    private Long bank_card_id;

    /**
     * 卡号
     */
    @ApiModelProperty(value="卡号")
    private String card_num;

    /**
     * 持卡人用户名
     */
    @ApiModelProperty(value="持卡人用户名")
    private String username;

    /**
     * 银行名称
     */
    @ApiModelProperty(value=" 银行名称")
    private String bank_name;

    /**
     * 开户银行地址
     */
    @ApiModelProperty(value="开户银行地址")
    private String bank_address;

    /**
     * 封面图
     */
    @ApiModelProperty(value="封面图")
    private String image;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Long create_time;

    private static final long serialVersionUID = 1L;

	public Long getBank_card_id() {
		return bank_card_id;
	}

	public void setBank_card_id(Long bank_card_id) {
		this.bank_card_id = bank_card_id;
	}

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_address() {
		return bank_address;
	}

	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
}