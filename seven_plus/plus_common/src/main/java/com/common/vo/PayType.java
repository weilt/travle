package com.common.vo;

/**
 * 充值的类型信息
 * @author XIAOGEGE
 */
public class PayType {
	
	private String token;
	private int payType;   // 1- 充值支付  ，2- 洗车支付，3- 打赏支付
	
	public PayType() {}
	
	/**
	 * 
	 * @param token
	 * @param payType  1- 充值支付  ，2- 洗车支付，3- 打赏支付
	 */
	public PayType(String token,int payType) {
		this.token = token;
		this.payType = payType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	
}
