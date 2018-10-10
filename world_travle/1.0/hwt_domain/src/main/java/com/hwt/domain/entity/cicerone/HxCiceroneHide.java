package com.hwt.domain.entity.cicerone;

import java.io.Serializable;

/**
 * @author 
 */
public class HxCiceroneHide implements Serializable {
    /**
     * 用户id
     */
    private Long user_id;

    /**
     * 描述
     */
    private String hide_dec;

    /**
     * 开始时间
     */
    private Long start_time;

    /**
     * 结束时间   为空说明是永久被封
     */
    private Long end_time;

    private static final long serialVersionUID = 1L;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getHide_dec() {
        return hide_dec;
    }

    public void setHide_dec(String hide_dec) {
        this.hide_dec = hide_dec;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }
}