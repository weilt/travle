package com.hx.core.wyim.service;

import com.hx.core.wyim.entity.SMSUser;

/**
 * 短信业务接口
 * @author Administrator
 *
 */
public interface SmsService {
	/**
	 * 发送短信/语音短信验证码
	 * 			描述---向指定的手机号码发送短信验证码。
	 * @return
	 * @throws Exception
	 */
	public String sendcode(SMSUser smsUser) throws Exception;
	
	/**
	 * 校验验证码
	 * 		描述---校验指定手机号的验证码是否合法。
	 * @param smsUser
	 * @return
	 * @throws Exception
	 */
	public String verifycode(SMSUser smsUser) throws Exception;
	
	
	/**
	 *  发送通知类和运营类短信
	 * 		描述---向手机号发送内容格式预定义的短信，整个短信的内容由模板和变量组成。
	 * @param smsUser
	 * @return
	 * @throws Exception
	 */
	public String sendtemplate(SMSUser smsUser) throws Exception;
	/**
	 *  查询通知类和运营类短信发送状态
	 * 		描述---根据短信的sendid(sendtemplate.action接口中的返回值)，查询短信发送结果。
	 * @param smsUser
	 * @return
	 * @throws Exception
	 */
	public String querystatus(SMSUser smsUser) throws Exception;
}
