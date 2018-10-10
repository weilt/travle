package com.domain.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.common.util.GsonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AdminAccesslog implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 系统ID
	 */
    private String id;
    /**
     * 操作内容
     */
    private String handleContent;
    /**
     * 配置键(就是进入该方法的路径)
     */
    private String configKey;
    /**
     * 创建人（操作人）
     */
    private Integer userId;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 当前操作机器的ip地址和名称
     */
    private String clientIpAndName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent == null ? null : handleContent.trim();
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
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

    public String getClientIpAndName() {
        return clientIpAndName;
    }

    public void setClientIpAndName(String clientIpAndName) {
        this.clientIpAndName = clientIpAndName == null ? null : clientIpAndName.trim();
    }
    
    
	public String toString() {
		return GsonUtil.toJson(this, AdminAccesslog.class);
	}
}