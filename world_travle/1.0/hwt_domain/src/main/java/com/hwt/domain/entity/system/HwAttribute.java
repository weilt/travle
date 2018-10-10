package com.hwt.domain.entity.system;

import java.io.Serializable;

/**
 * @author 
 */
public class HwAttribute implements Serializable {
    /**
     * id
     */
    private Integer attribute_id;

    /**
     * 名称
     */
    private String attribute_name;

    /**
     * 属性的key  ，通过这个来查找
     */
    private String attribute_key;

    /**
     * 描述
     */
    private String attribute_dec;

    /**
     * 是否删除 0-否1-是
     */
    private Integer is_hide;

    /**
     * 修改时间
     */
    private Long update_time;

    /**
     * 创建时间
     */
    private Long create_time;

    private static final long serialVersionUID = 1L;

    public Integer getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(Integer attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public String getAttribute_key() {
        return attribute_key;
    }

    public void setAttribute_key(String attribute_key) {
        this.attribute_key = attribute_key;
    }

    public String getAttribute_dec() {
        return attribute_dec;
    }

    public void setAttribute_dec(String attribute_dec) {
        this.attribute_dec = attribute_dec;
    }

    public Integer getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(Integer is_hide) {
        this.is_hide = is_hide;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}