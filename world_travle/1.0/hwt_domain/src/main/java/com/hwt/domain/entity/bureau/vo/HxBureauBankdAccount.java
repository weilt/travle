package com.hwt.domain.entity.bureau.vo;

/**
 * 旅行社注册银行账户基本信息
 * @author Administrator
 *
 */
public class HxBureauBankdAccount {
	
	/**
	 * 银行账户
	 */
	private String bureau_bank_account;
	
	/**
	 * 银行名称
	 */
	private String bank_name;
	
	/**
	 * 户名
	 */
	private String legal_person ;

	public String getBureau_bank_account() {
		return bureau_bank_account;
	}

	public void setBureau_bank_account(String bureau_bank_account) {
		this.bureau_bank_account = bureau_bank_account;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getLegal_person() {
		return legal_person;
	}

	public void setLegal_person(String legal_person) {
		this.legal_person = legal_person;
	}
	
	
}
