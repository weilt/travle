package com.hx.bureau.aliyun.file.oss;

import com.hx.bureau.aliyun.file.type.FileException;

/**
 * @author XiaoCanyun
 */
public class OSSFileException extends FileException {
	
	private static final long serialVersionUID = 2785930024289986973L;

	public OSSFileException() {
		super();
	}

	public OSSFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public OSSFileException(String message) {
		super(message);
	}

	public OSSFileException(Throwable cause) {
		super(cause);
	}
	
	

}
