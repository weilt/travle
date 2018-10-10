package com.common.result;



import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/5.
 * 请求响应统一返回对象
 */
public class ResultEntity implements Serializable{

    private static final long serialVersionUID = -4710251775996516937L;
   
    private  String msg;
    
    private Object data;
    
    private int code;

    public ResultEntity() {
        this.msg = ResultCode.SUCCESS.msg;
        this.code = ResultCode.SUCCESS.code;
    }
    
    public ResultEntity(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }


    public ResultEntity(Object data) {
        this.msg = ResultCode.SUCCESS.msg;
        this.code = ResultCode.SUCCESS.code;
        this.data = data;
    }

    public ResultEntity(ResultCode resultCode) {
        this.msg = resultCode.msg;
        this.code = resultCode.code;
        this.data = null;
    }

    public ResultEntity(Object data,ResultCode resultCode) {
        this.msg = resultCode.msg;
        this.code = resultCode.code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
