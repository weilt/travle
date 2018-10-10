package com.hwt.domain.entity.cicerone;

import java.io.Serializable;

/**
 * @author 
 */
public class HxCiceroneRelevance implements Serializable {
    /**
     * ID自增
     */
    private Long id;

    /**
     * 导游ID
     */
    private Long cic_id;

    /**
     * 导游类型ID
     */
    private Long type_id;

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

    public Long getCic_id() {
        return cic_id;
    }

    public void setCic_id(Long cic_id) {
        this.cic_id = cic_id;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}