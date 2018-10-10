package com.hwt.domain.entity.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 
 */
public class AdminSystemconfig implements Serializable {
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

    private static final long serialVersionUID = 1L;



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
        this.displayName = displayName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
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
        this.description = description;
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
}