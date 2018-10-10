package com.hx.admin.service.cache;

import java.io.Serializable;

/**
 * Created by Ro on 2018/4/23.
 * 后台用户缓存对象
 */
public class AdminUserCache implements Serializable {

    private Long adminUserId;       //用户ID
    private String adminUserAccount;//用户账号
    private String adminUserName;   //用户名称
    private Integer roleId;     	//角色id

    

    public AdminUserCache(Long adminUserId, String adminUserAccount, String adminUserName, Integer roleId) {
		super();
		this.adminUserId = adminUserId;
		this.adminUserAccount = adminUserAccount;
		this.adminUserName = adminUserName;
		this.roleId = roleId;
	}

	public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminUserAccount() {
        return adminUserAccount;
    }

    public void setAdminUserAccount(String adminUserAccount) {
        this.adminUserAccount = adminUserAccount;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
    
    
}
