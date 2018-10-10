package com.hwt.domain.entity.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 
 */
public class AdminModule implements Serializable {
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

    private static final long serialVersionUID = 1L;



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