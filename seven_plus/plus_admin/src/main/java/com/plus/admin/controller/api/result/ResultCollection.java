package com.plus.admin.controller.api.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 09:30
 * @Description:
 */
@ApiModel(value="统一返回（列表）")
public class ResultCollection<T> implements Serializable {

    @ApiModelProperty(value = "状态码")
    private Long code;

    @ApiModelProperty(value = "消息")
    private String msg;

    /**
     * 当前页返回数据列表
     */
    @ApiModelProperty(value = "数据")
    private Collection<T> data;


    public ResultCollection(Long code, String msg, Collection<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultCollection<T> build(Collection<T> results) {
        return new ResultCollection(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),results);
    }


    public static <T> ResultCollection<T> build(ResultCode code){
        return new ResultCollection(code.getCode(),code.getMsg(),null);
    }

    public static <T> ResultCollection<T> build(){
        return new ResultCollection(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg(),null);
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

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }
}
