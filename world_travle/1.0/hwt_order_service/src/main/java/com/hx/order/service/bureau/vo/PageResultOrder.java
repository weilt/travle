package com.hx.order.service.bureau.vo;

import java.util.Map;

import com.hx.core.page.asyn.PageResult;

/**
 * 订单列表
 * @author Administrator
 *
 */
public class PageResultOrder extends PageResult<Map<String, Object>> {
	
	
	
	private Long status0;//全部订单

	private Long status1;//待确认
	
	private Long status2;//待开始
	
	private Long status3;//进行中
	
	private Long status4;//已完成
	
	private Long status5;//退款订单

	public Long getStatus0() {
		return status0;
	}

	public void setStatus0(Long status0) {
		this.status0 = status0;
	}

	public Long getStatus1() {
		return status1;
	}

	public void setStatus1(Long status1) {
		this.status1 = status1;
	}

	public Long getStatus2() {
		return status2;
	}

	public void setStatus2(Long status2) {
		this.status2 = status2;
	}

	public Long getStatus3() {
		return status3;
	}

	public void setStatus3(Long status3) {
		this.status3 = status3;
	}

	public Long getStatus4() {
		return status4;
	}

	public void setStatus4(Long status4) {
		this.status4 = status4;
	}

	public Long getStatus5() {
		return status5;
	}

	public void setStatus5(Long status5) {
		this.status5 = status5;
	}

}
