package com.hwt.domain.entity.admin;

import java.io.Serializable;

/**
 * @author 
 */
public class AdminRolemodule implements Serializable {
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

    private static final long serialVersionUID = 1L;


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
}