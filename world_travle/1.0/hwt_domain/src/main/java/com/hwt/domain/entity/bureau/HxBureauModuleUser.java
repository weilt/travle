package com.hwt.domain.entity.bureau;

import java.io.Serializable;

/**
 * @author 
 */
public class HxBureauModuleUser implements Serializable {
    private Long id;

    /**
     * 旅行社模块id
     */
    private Long bureau_module_id;

    /**
     * 旅行社用户id
     */
    private Long bureau_user_id;

    /**
     * 创建时间
     */
    private Long create_time;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBureau_module_id() {
        return bureau_module_id;
    }

    public void setBureau_module_id(Long bureau_module_id) {
        this.bureau_module_id = bureau_module_id;
    }

    public Long getBureau_user_id() {
        return bureau_user_id;
    }

    public void setBureau_user_id(Long bureau_user_id) {
        this.bureau_user_id = bureau_user_id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}