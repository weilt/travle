package com.hx.bureau.service.Vo;

import java.util.Map;

import com.hwt.domain.entity.cicerone.HxCiceroneInfo;
import com.hx.core.page.asyn.PageResult;

/**
 * 
 * @author Administrator
 *
 */
public class PageResultHxLine extends PageResult<Map<String, Object>> {
	
	
	
	private Long online;//上线数

	private Long downline;//下线数

	public Long getOnline() {
		return online;
	}

	public void setOnline(Long online) {
		this.online = online;
	}

	public Long getDownline() {
		return downline;
	}

	public void setDownline(Long downline) {
		this.downline = downline;
	}
	
	
}
