package com.hx.core.wyim.entity.system;

import com.hx.core.utils.IDUtils;
import com.hx.core.utils.UUIDHelper;

/**
 * 系统通知
 * @author Administrator
 *
 */
public class SystemNotice {
	
	private String title; //标题
	
	private String description;//描述
	
	private String url;//地址
	
	//1 文本 2 账单 3 html//   4 导游拒绝或者取消或者评价 5 线路被确认或者拒绝或者取消或者评价//   6 导游被取消了 7 其他消息 //8 导游确认
	private int type;          
	
	private Long createTime; //创建时间
	
	private String actualId;    //查询依据的id
	
	private String aboutBills;  //账单实体

	private String systemMsgId;//消息id
	
	public SystemNotice(String title, String description, String url, int type, Long createTime, String actualId,
			String aboutBills) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.type = type;
		this.createTime = createTime;
		this.actualId = actualId;
		this.aboutBills = aboutBills;
		this.systemMsgId = UUIDHelper.createUUId();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getActualId() {
		return actualId;
	}

	public void setActualId(String actualId) {
		this.actualId = actualId;
	}

	public String getAboutBills() {
		return aboutBills;
	}

	public void setAboutBills(String aboutBills) {
		this.aboutBills = aboutBills;
	}

	public String getSystemMsgId() {
		return systemMsgId;
	}

	public void setSystemMsgId(String systemMsgId) {
		this.systemMsgId = systemMsgId;
	}
	
}
