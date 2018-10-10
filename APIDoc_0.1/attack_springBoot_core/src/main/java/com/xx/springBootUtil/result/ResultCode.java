package com.xx.springBootUtil.result;

/**
 * 返回参数信息
 * @author LiuGang
 */
public enum ResultCode {
	
 	SUCCESS( 200,"成功!"),
    ERROR( 300,"错误!"),
    /**
     * 系统异常编码
     */
    SYS_ERROR( 100,"系统错误"),
    LOGIN_OUT( 101,"未登录"),
    SESSION_OUT(102,"登录超时"),
    OPT_ERROR(103,"非法操作"),
    REQUEST_PRAM(104,"请求参数错误");

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code;//编码
    public String msg; //提示错误

    public static ResultCode getResultCode(String name) {
        if(isExist(name)) {
            return ResultCode.valueOf(name);
        }
        return null;
    }
    private static boolean isExist(String name) {
        ResultCode[] rc = ResultCode.values();
        for (int i = rc.length - 1; i >= 0; i--) {
            if(rc[i].name().equals(name)) return true;
        }
        return false;
    }
}
