package com.hx.user.service;

public interface SmsMessageService {
	/**
	 * 发送注册短信验证码
	 * @param phone
	 * @return
	 */
	public String sendRegisterIdentifyCode(String phone) throws Exception;

	/**
	 * 密码找回短信验证码
	 * @param phone
	 * @return
	 */
	public String sendFindPwdIdentifyCode(String phone) throws Exception;

	/**
	 * 登陆短信验证码
	 * @param phone
	 * @return
	 */
	public String sendLoginIdentifyCode(String phone) throws Exception;

	/**
	 * 下单验证码
	 * @param phone
	 * @return
	 */
	public String sendInsertOrderIdentifyCode(String phone) throws Exception ;

	/**
	 * 第一次设置支付密码
	 * @param phone
	 * @return
	 */
	public String sendPaymentPassWordIdentifyCode(String phone) throws Exception ;
	
	/**
	 * 重置支付密码
	 * @param phone
	 * @return
	 */
	public String sendResetPaymentPassIdentifyCode(String phone) throws Exception ;

	/**
	 * 支付验证
	 * @param phone
	 * @return
	 */
	public String sendWalletPayIdentifyCode(String phone) throws Exception;

	/**
	 * 提现验证
	 * @param phone
	 * @return
	 */
	public String sendPutForwardIdentifyCode(String phone) throws Exception;

}
