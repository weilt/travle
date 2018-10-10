package com.hx.core.wyim.entity.emu;

/**
 * 通知类短息
 * @author Administrator
 *
 */
public enum SMSNotice {
	
	/**
     * 旅行社入驻成功
     */
	bureausuccess(4023508,"尊敬的{contacts_name}用户你好，您提交的旅行社商家入驻资料，已审核通过！以下是您的后台管理信息，\n\t地址{url}\n\t账号{legal_person_phome}\n\t密码{passWord}\n请务必妥善保存！"),
	
	/**
     * 旅行社入驻失败
     */
	bureaufail(4023507,"尊敬的{contacts_name}用户你好，您提交的旅行社商家入驻资料，并未审核通过！以下是您未通过审核的原因：\n\t{reason}\n请重新提交入驻资料，确认您提交的信息符合提示的要求。"),
	
	/**
	 * 旅行社下单
	 */
	bureauorder(4023507,"尊敬的旅游平台商，您有一个新的订单消息，请及时处理；如{time}内不做处理，订单将自动取消"),
	
	/**
	 * 旅行社订单被取消
	 */
	bureauordercancel(4023507,"尊敬的旅游平台商，您有一个订单已被买家取消，请登录后台查看详情。订单编号：{order_num}。");
	
    SMSNotice(int smsTypeTemplateNumber,String smsTypeLogMsg){
    	this.smsTypeTemplateNumber = smsTypeTemplateNumber;
    	this.smsTypeLogMsg = smsTypeLogMsg;
    }
    
    /**
     * 短信模板编号
     */
    public int smsTypeTemplateNumber;
    
    /**
     * 短信码日志消息模板
     */
    public String smsTypeLogMsg;
}
