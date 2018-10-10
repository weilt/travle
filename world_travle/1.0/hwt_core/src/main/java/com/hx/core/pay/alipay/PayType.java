package com.hx.core.pay.alipay;

/**
 * 充值的类型信息
 * @author XIAOGEGE
 */
public class PayType {
	
	private Long user_id;
	private int payType;   // 1- 充值支付  ，2- 订单支付
	
	public PayType() {}
	
	/**
	 * 
	 * @param user_id
	 * @param payType  1- 充值支付  ，2- 洗车支付
	 */
	public PayType(Long user_id, int payType) {
		super();
		this.user_id = user_id;
		this.payType = payType;
	}
	
	public int getPayType() {
		return payType;
	}
	

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	
}
