package com.common.excption;

/**自定义异常处理事件类型
 * @author
 */
public class BaseException extends RuntimeException{
	/**
	 * 自定义异常处理
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
	private Long code;
	
	public BaseException(BaseExceptionConstants baseExceptionConst) {
        super(baseExceptionConst.getMessage());
        this.code = baseExceptionConst.getCode();

    }

    public BaseException(BaseExceptionConstants baseExceptionConstant, Throwable cause){
        super(baseExceptionConstant.getMessage(), cause);
        this.code = baseExceptionConstant.getCode();
    }

    public BaseException(String message) {
        super(message);
    }

    public static BaseException build(BaseExceptionConstants baseExceptionConst) {
        return new BaseException(baseExceptionConst);
    }
    public static BaseException build(BaseExceptionConstants baseExceptionConstant, Throwable cause) {
        return new BaseException(baseExceptionConstant, cause);
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

    public Long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
