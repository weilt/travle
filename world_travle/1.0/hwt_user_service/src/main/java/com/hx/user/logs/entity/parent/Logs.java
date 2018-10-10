package com.hx.user.logs.entity.parent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by RO on 2017/11/9.
 * 日志基类
 */
public class Logs implements Serializable{
    private static final long serialVersionUID = 1122740424158115068L;
    
   

    protected String remarks;           //日志备注 记录其他信息
    protected Long timeStamp;           //时间戳用于查询
    protected Date createTime; //日志记录时间

    public Logs(){};

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
