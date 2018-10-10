package com.hx.core.exception;

/**自定义异常处理事件类型
 * @author LiuGang
 */
public class BaseException extends RuntimeException{
	/**
	 * 自定义异常处理
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
