package com.hwt.domain.entity.bureau;

import java.io.Serializable;

/**
 * @author 
 */
public class HxBureauModule implements Serializable {
    /**
     * id
     */
    private Long bureau_module_id;

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
    private Integer parent_id;

    /**
     * 模块图标
     */
    private String module_image;

    /**
     * 模块描述
     */
    private String description;

    /**
     * 模块排序
     */
    private Integer sort;

    /**
     * 是否隐藏 1-否 2-是
     */
    private Integer is_hide;

    /**
     * 创建人
     */
    private Long user_id;

    /**
     * 创建时间
     */
    private Long create_time;

    private static final long serialVersionUID = 1L;

    public Long getBureau_module_id() {
        return bureau_module_id;
    }

    public void setBureau_module_id(Long bureau_module_id) {
        this.bureau_module_id = bureau_module_id;
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

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getModule_image() {
        return module_image;
    }

    public void setModule_image(String module_image) {
        this.module_image = module_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(Integer is_hide) {
        this.is_hide = is_hide;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}