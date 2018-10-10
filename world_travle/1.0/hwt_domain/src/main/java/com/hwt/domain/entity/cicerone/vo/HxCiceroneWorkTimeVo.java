package com.hwt.domain.entity.cicerone.vo;
/**
 * 导游工作时间
 * @author Administrator
 *
 */
public class HxCiceroneWorkTimeVo {
	
	/**
	 * 日期  如  2018-09-01
	 */
	private String work_time;
	
	/**
	 * 状态   0-未被预约   1-被预约了
	 */
	private Integer status;

	public String getWork_time() {
		return work_time;
	}

	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
