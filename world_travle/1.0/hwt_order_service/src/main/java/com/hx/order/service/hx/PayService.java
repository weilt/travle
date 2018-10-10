package com.hx.order.service.hx;

public interface PayService {

	/**
	 * 零钱支付
	 * @param user_id
	 * @param order_num
	 * @param payment_password
	 * @param smsVerify 
	 */
	void wallet_pay(Long user_id, String order_num, String payment_password, String smsVerify) throws Exception ;

	/**
	 * 支付宝生成预付单
	 * @param token 
	 * @param user_id
	 * @param order_num
	 * @param string
	 * @return
	 */
	Object zhifubao_pay(String token, Long user_id, String order_num);

	/**
	 * 支付回调信息 通知处理
	 * @param out_trade_no - 订单ID
	 * @param attach - 自定义参数信息
	 * @param type - 1-微信，2-支付宝
	 */
	void pay_callBack(String out_trade_no, String attach, int type) throws Exception ;

}
