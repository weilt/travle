package com.hwt.domain.entity.cicerone;

import java.io.Serializable;

/**
 * @author 
 */
public class HxCiceroneType implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 类型名称
     */
    private String type_name;

    /**
     * 类型值
     */
    private String type_value;

    /**
     * 描述
     */
    private String type_description;

    /**
     * 是否隐藏 0-否 1-是
     */
    private Integer is_hide;

    /**
     * 创建人ID
     */
    private Long create_id;

    /**
     * 创建时间
     */
    private Long create_time;

    /**
     * 修改时间
     */
    private Long update_time;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_value() {
        return type_value;
    }

    public void setType_value(String type_value) {
        this.type_value = type_value;
    }

    public String getType_description() {
        return type_description;
    }

    public void setType_description(String type_description) {
        this.type_description = type_description;
    }

    public Integer getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(Integer is_hide) {
        this.is_hide = is_hide;
    }

    public Long getCreate_id() {
        return create_id;
    }

    public void setCreate_id(Long create_id) {
        this.create_id = create_id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }
}