package com.plus.admin.controller.api.result;

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
    REQUEST_PRAM(104,"请求参数错误"),
    VERIFICATION_CODE_ERR(105,"验证码错误"),
    UPLOAD_IMG_ERR(106,"上传图片失败"),
    EXIST_NOT_PHONE(107,"未绑定手机号"),
    ;

    ResultCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long code;//编码
    public String msg; //提示错误

    public long getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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
