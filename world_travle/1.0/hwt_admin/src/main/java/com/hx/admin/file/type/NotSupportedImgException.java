package com.hx.admin.file.type;

import com.hx.admin.file.oss.OSSFileException;

public class NotSupportedImgException extends OSSFileException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1540908106036201250L;

	public NotSupportedImgException() {
		super("不受支持的图片格式类型");
	}

	public NotSupportedImgException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSupportedImgException(String message) {
		super(message);
	}

	public NotSupportedImgException(Throwable cause) {
		super(cause);
	}
	
	
	

}
