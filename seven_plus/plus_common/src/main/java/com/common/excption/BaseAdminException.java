package com.common.excption;

/**自定义异常处理事件类型
 * @author LiuGang
 */
public class BaseAdminException extends RuntimeException{
	/**
	 * 自定义异常处理
	 */
	private static final long serialVersionUID = 1L;

	public BaseAdminException() {
    }

    public BaseAdminException(String message) {
        super(message);
    }

    public BaseAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseAdminException(Throwable cause) {
        super(cause);
    }

    public BaseAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
