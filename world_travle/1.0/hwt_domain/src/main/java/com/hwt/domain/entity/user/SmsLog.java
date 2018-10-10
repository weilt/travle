package com.hwt.domain.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 短信-日志表
 * @author JIAO_LIU_BABA
 */
public class SmsLog implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;

	private String id;//

	private String smsPhone;//

	private String smsMessage;//

	private Integer smsType;//

	private Date creationTime;//
	
	
	public SmsLog(){
	}
	public SmsLog(String id,String smsPhone,String smsMessage,Integer smsType,Date creationTime){
		this.id = id;
		this.smsPhone = smsPhone;
		this.smsMessage = smsMessage;
		this.smsType = smsType;
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public String getSmsMessage() {
		return smsMessage;
	}

	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}

	public Integer getSmsType() {
		return smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}



}
