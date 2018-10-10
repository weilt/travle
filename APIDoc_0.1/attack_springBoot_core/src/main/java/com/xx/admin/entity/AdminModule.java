package com.xx.admin.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xx.admin.util.ApacheSecurityUtil;
import com.xx.springBootUtil.util.GsonUtil;

/**
 * 权限内容表
 * @author LiuGang
 */
public class AdminModule implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 系统ID
	 */
    private Integer id;
    
    /**
     * id加密
     */
    private String encryptionId;
    /**
     * 模块名称
     */
    private String name;
    /**
     * 模块地址
     */
    private String url;
    /**
     * 父级模块Id
     */
    private Integer parentId;
    /**
     * 模块图标
     */
    private String moduleImage;
    /**
     * 模块描述
     */
    private String description;
    /**
     * 模块排序
     */
    private Byte sort;
    /**
     * 是否隐藏 1-否 2-是
     */
    private Byte isHide;
    /**
     * 创建人
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
				
				e.printStackTrace();
			}
    	}
		return encryptionId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getModuleImage() {
        return moduleImage;
    }

    public void setModuleImage(String moduleImage) {
        this.moduleImage = moduleImage == null ? null : moduleImage.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Byte getIsHide() {
        return isHide;
    }

    public void setIsHide(Byte isHide) {
        this.isHide = isHide;
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
		return GsonUtil.toJson(this, AdminModule.class);
	}
}