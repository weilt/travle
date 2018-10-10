package com.hx.api.base;
import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/8/5.
 * 请求响应统一返回对象
 */
public class ResultEntity implements Serializable{

    private static final long serialVersionUID = -4710251775996516937L;
    private  String msg;
    @ApiModelProperty(value="数据")
    private Object data;
    @ApiModelProperty(value="状态码 ",name="状态码 ")
    private String code;

    public ResultEntity() {
        this.msg = ResultCode.SUCCESS.msg;
        this.code = ResultCode.SUCCESS.code;
    }
    
    public ResultEntity(String code, String msg) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
