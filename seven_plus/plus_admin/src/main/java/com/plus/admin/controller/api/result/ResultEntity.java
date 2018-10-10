package com.plus.admin.controller.api.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 09:19
 * @Description: api统一返回
 */
@ApiModel(value="统一返回（单实体）")
public class ResultEntity<T> implements Serializable {

    @ApiModelProperty(value = "状态码")
    private Long code;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T data;


    public ResultEntity(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultEntity(Long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultEntity(T t) {
        this.data = t;
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T> ResultEntity<T> build(T value) {
        return new ResultEntity(value);
    }

    public static <T> ResultEntity<T> build(Long code, String msg){
        return new ResultEntity(code,msg);
    }

    public static <T> ResultEntity<T> build(ResultCode resultCode,T value){
        return new ResultEntity(resultCode.getCode(),resultCode.getMsg(),value);
    }


    public static <T> ResultEntity<T> build(ResultCode resultCode){
        return new ResultEntity(resultCode.getCode(),resultCode.getMsg(),null);
    }

    public static <T> ResultEntity<T> build(){
        return build(ResultCode.SUCCESS);
    }
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
