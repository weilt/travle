package com.domain.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.common.util.GsonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 配置文件信息
 * @author LiuGang
 *
 */
public class AdminSystemconfig implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id编号
	 */
    private Integer id;
    /**
     * 显示名称
     */
    private String displayName;
    /**
     * 配置键
     */
    private String configKey;
    /**
     * 值
     */
    private String configValue;
    /**
     * 是否只读 0 否 1 是
     */
    private Byte isReadOnly;
    /**
     * 描述
     */
    private String description;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public Byte getIsReadOnly() {
        return isReadOnly;
    }

    public void setIsReadOnly(Byte isReadOnly) {
        this.isReadOnly = isReadOnly;
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
		return GsonUtil.toJson(this, AdminSystemconfig.class);
	}
}