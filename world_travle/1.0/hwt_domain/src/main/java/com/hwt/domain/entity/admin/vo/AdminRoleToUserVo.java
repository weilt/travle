package com.hwt.domain.entity.admin.vo;

import java.io.Serializable;

/**
 * 获取角色信息给用户操作
 * @author Administrator
 *
 */
public class AdminRoleToUserVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	private Integer role_id;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
