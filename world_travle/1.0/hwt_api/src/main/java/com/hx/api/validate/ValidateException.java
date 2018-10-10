package com.hx.api.validate;


/**
 * 自定义验证异常
 * @author WJ
 *
 */
public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = -3141819290313887525L;

	public ValidateException() {
		super();
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(Throwable cause) {
		super(cause);
	}
}
