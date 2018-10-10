package com.hx.core.logs.entity.parent;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by RO on 2017/11/9.
 * 日志基类
 */
public class Logs implements Serializable{
    private static final long serialVersionUID = 1122740424158115068L;

    protected String remarks;           //日志备注 记录其他信息
    protected Long creat_time;           //时间

    protected Logs(){};

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public Long getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(Long creat_time) {
		this.creat_time = creat_time;
	}
}
