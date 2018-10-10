package com.hwt.domain.entity.mg.user;

import java.io.Serializable;

/**
 * 朋友圈id管理
 * @author Administrator
 *
 */
public class FriendCircleIdAdministration implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Long friendCircleIdAdministrationId;
	
	/**
	 * 创建时间
	 */
	private Long creat_time;
	
	/**
	 * 备注
	 */
	private String Remarks;
	

	
	public FriendCircleIdAdministration(Long friendCircleIdAdministrationId, Long creat_time, String remarks) {
		super();
		this.friendCircleIdAdministrationId = friendCircleIdAdministrationId;
		this.creat_time = creat_time;
		Remarks = remarks;
	}

	public Long getFriendCircleIdAdministrationId() {
		return friendCircleIdAdministrationId;
	}

	public void setFriendCircleIdAdministrationId(Long friendCircleIdAdministrationId) {
		this.friendCircleIdAdministrationId = friendCircleIdAdministrationId;
	}

	public Long getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(Long creat_time) {
		this.creat_time = creat_time;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	
	
}
