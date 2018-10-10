package com.hx.core.wyim.entity;

import java.io.Serializable;

/**
 * 短信
 * @author Administrator
 *
 */
public class SMSUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 目标手机号
	 */
	private String mobile;
	/**
	 * 目标设备号，可选参数
	 */
	private String deviceId;
	/**
	 * 模板编号(如不指定则使用配置的默认模版)
	 */
	private int templateid;
	/**
	 * 验证码长度，范围4～10，默认为4
	 */
	private int codeLen;
	/**
	 * 客户自定义验证码，长度为4～10个数字如果设置了该参数，则codeLen参数无效
	 */
	private String authCode;
	/**
	 * 是否需要支持短信上行。true:需要，false:不需要说明：如果开通了短信上行抄送功能，该参数需要设置为true，其它情况设置无效
	 */
	private boolean needUp;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 接收者号码列表，JSONArray格式,如["186xxxxxxxx","186xxxxxxxx"]，限制接收者号码个数最多为100个
	 */
	private String[] mobiles;
	/**
	 * 短信参数列表，用于依次填充模板，JSONArray格式，每个变量长度不能超过30字，如["xxx","yyy"];对于不包含变量的模板，不填此参数表示模板即短信全文内容
	 */
	private String[] params;
	/**
	 * 发送短信的编号sendid
	 */
	private long sendid;
	
	
	/**
	 * 构造方法----发送短信/语音短信验证码 
	 * @param mobile
	 * @param deviceId
	 * @param templateid
	 * @param codeLen
	 * @param authCode
	 * @param needUp
	 */
	public SMSUser(String mobile, String deviceId, int templateid, int codeLen, String authCode, boolean needUp) {
		super();
		this.mobile = mobile;
		this.deviceId = deviceId;
		this.templateid = templateid;
		this.codeLen = codeLen;
		this.authCode = authCode;
		this.needUp = needUp;
	}


	/**
	 * 构造方法----校验验证码
	 * @param mobile
	 * @param code
	 */
	public SMSUser(String mobile, String code) {
		super();
		this.mobile = mobile;
		this.code = code;
	}


	/**
	 * 构造方法----发送通知类和运营类短信
	 * @param templateid
	 * @param needUp
	 * @param mobiles
	 * @param params
	 */
	public SMSUser(int templateid, boolean needUp, String[] mobiles, String[] params) {
		super();
		this.templateid = templateid;
		this.needUp = needUp;
		this.mobiles = mobiles;
		this.params = params;
	}


	/**
	 *  构造方法----查询通知类和运营类短信发送状态
	 * @param sendid
	 */
	public SMSUser(long sendid) {
		super();
		this.sendid = sendid;
	}
	public SMSUser() {
		super();
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public int getTemplateid() {
		return templateid;
	}


	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}


	public int getCodeLen() {
		return codeLen;
	}


	public void setCodeLen(int codeLen) {
		this.codeLen = codeLen;
	}


	public String getAuthCode() {
		return authCode;
	}


	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}


	public boolean getNeedUp() {
		return needUp;
	}


	public void setNeedUp(boolean needUp) {
		this.needUp = needUp;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String[] getMobiles() {
		return mobiles;
	}


	public void setMobiles(String[] mobiles) {
		this.mobiles = mobiles;
	}


	public String[] getParams() {
		return params;
	}


	public void setParams(String[] params) {
		this.params = params;
	}


	public long getSendid() {
		return sendid;
	}


	public void setSendid(long sendid) {
		this.sendid = sendid;
	}
	
	
	
	
}
