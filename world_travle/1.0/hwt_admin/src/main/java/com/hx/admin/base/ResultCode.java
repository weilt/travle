package com.hx.admin.base;

/**
 * Created by Administrator on 2017/8/5.
 */
public enum ResultCode {

    SUCCESS("200","成功!"),
    ERROR("300","错误!"),
    /**
     * 系统异常编码
     */
    SYS_ERROR("100","系统错误"),
    LOGIN_OUT("101","未登录"),
    SESSION_OUT("102","登录超时"),
    OPT_ERROR("103","非法操作"),
    REQUEST_PRAM("104","请求参数错误")

    /**
     * 业务异常编码从300、400 开始
     */
    ;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code;
    public String msg;

    public static ResultCode getResultCode(String name) {
        if(isExist(name)) {
            return ResultCode.valueOf(name);
        }
//        return ResultCode.ERROR;
        return null;
    }

    private static boolean isExist(String name) {
        ResultCode[] rc = ResultCode.values();
        for (int i = rc.length - 1; i >= 0; i--) {
            if(rc[i].name().equals(name)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(getResultCode("2001").code);
    }
}
