package com.hx.api.aliyun.file.type;




/**
 * 文件异常信息
 * @author XiaoCanyun
 *
 */
public class FileException extends RuntimeException {

	private static final long serialVersionUID = -7234259481697032655L;

	public FileException() {
		super();
	}

	public FileException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileException(String message) {
		super(message);
	}

	public FileException(Throwable cause) {
		super(cause);
	}




}
