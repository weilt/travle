package com.xx.springBootUtil.excption;

/**
 * 自定义 验证 返回异常 处理
 * @author LiuGang
 */
public class ValidateException extends RuntimeException{
	/**
	 * 自定义异常处理
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    public ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
