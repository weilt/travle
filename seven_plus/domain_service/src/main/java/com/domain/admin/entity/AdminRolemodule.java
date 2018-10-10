package com.domain.admin.entity;

import java.io.Serializable;

import com.common.util.GsonUtil;

/**
 * 角色信息表
 * @author LiuGang
 */
public class AdminRolemodule implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 模块Id
     */
    private Long moduleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
    
    public String toString() {
		return GsonUtil.toJson(this, AdminRolemodule.class);
	}
}