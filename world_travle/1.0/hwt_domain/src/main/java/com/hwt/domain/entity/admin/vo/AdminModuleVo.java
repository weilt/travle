package com.hwt.domain.entity.admin.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 模块返回信息
 * @author Administrator
 *
 */
public class AdminModuleVo implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * id
     */
    private Integer id;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块地址
     */
    private String url;

    /**
     * 父级模块
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
    private String user_account;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

	public String getModuleImage() {
		return moduleImage;
	}

	public void setModuleImage(String moduleImage) {
		this.moduleImage = moduleImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	
    
    
}
