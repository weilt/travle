package com.hx.core.wyim.entity.emu;

/**
 * Created by Ro on 2018/4/12.
 * 短信验证码枚举类型
 */
public enum  SMSType {
    /**
     * 注册短信码
     */
    register("HXJLRegister_","【淮信科技】 你正在注册世界之旅账号，验证码{code}，请勿转发。",3982563, 600),
    /**
     * 登陆短信码
     */
    login("HXJLLogin_","【淮信科技】 你正在登录世界之旅，验证码{code}，转发可能导致账号被盗，如果非本人操作请忽略。",3952521, 600),
    /**
     * 密码找回短信码
     */
    findPass("HXJLFindPass_","【淮信科技】 此验证码只用于修改或找回账户密码，验证码{code}，验证码提供给他人讲导致账号被盗，如果非本人操作请忽略。",3952522, 600),
	
	/**
	 * 下单验证码
	 */
	order("HXJLOrder_","【淮信科技】 此验证码只用于下单验证，验证码{code}，如果非本人操作请忽略。",9344061, 600),
	
	/**
	 * 重置支付密码
	 */
	resetPaymentPass("HXJLResetPaymentPassWord_","【淮信科技】 您正在重置走吧支付密码，您的验证码为：{code}，如果非本人操作请忽略。",9344060, 600),
	
	/**
	 * 第一次设置支付密码
	 */
	paymentPassWord("HXJLPaymentPassWord_","【探鹿科技】您正在设置走吧支付密码，您的验证码为：{code}，如果非本人操作请忽略。",9444083, 600), 
	
	/**
	 * 支付验证码
	 */
	walletPay("HXJLWalletPay_","【探鹿科技】您本次的支付验证码是：{code}，请勿将校验码泄露给他人，如非本人操作，请忽略本短信。",9304163, 600), 
	
	/**
	 * 提现验证码
	 */
	putForward("HXJLPutForward_","【探鹿科技】您本次的提现验证码是：{code}，请勿将校验码泄露给他人，如非本人操作，请忽略本短信。",9344106, 600);

    SMSType(String smsTypePrefix, String smsTypeLogMsg, int smsTypeTemplateNumber,int cacheTimeout) {
        this.smsTypePrefix = smsTypePrefix;
        this.smsTypeLogMsg = smsTypeLogMsg;
        this.smsTypeTemplateNumber = smsTypeTemplateNumber;
        this.cacheTimeout = cacheTimeout;
    }

    /**
     * 短信码缓存前缀
     */
    public String smsTypePrefix;
    /**
     * 短信码日志消息模板
     */
    public String smsTypeLogMsg;
    /**
     * 短信模板编号
     */
    public int smsTypeTemplateNumber;

    /**
     * 缓存超时时间
     */
    public int cacheTimeout;

}
