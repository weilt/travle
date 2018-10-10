package com.xx.admin.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xx.admin.util.ApacheSecurityUtil;
import com.xx.springBootUtil.util.GsonUtil;

/**
 * 角色信息类
 * @author LiuGang
 */
public class AdminRole implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 角色Id
	 */
    private Integer id;
    
    /**
     * id加密
     */
    private String encryptionId;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色数量
     */
    private Integer number;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 创建人Id 1-表示超级管理员
     */
    private Integer userId;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Integer getId() {
		return id;
    	
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEncryptionId() {
		if (StringUtils.isNotBlank(encryptionId)){
		    		
		    return encryptionId;
    	}else{
    		//加密
    		try {
    			
				return ApacheSecurityUtil.aes(id+"",ApacheSecurityUtil.aes);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("不要修改参数");
			}
    	}
	}

	public void setEncryptionId(String encryptionId) {
		this.encryptionId = encryptionId;
	}
	
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String toString() {
		return GsonUtil.toJson(this, AdminRole.class);
	}
}