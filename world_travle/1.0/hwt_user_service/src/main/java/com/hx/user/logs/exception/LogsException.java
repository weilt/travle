package com.hx.user.logs.exception;

/**
 * Created by RO on 2017/11/9.
 */
public class LogsException extends Exception {

    public LogsException() {
    }

    public LogsException(String message) {
        super(message);
    }

    public LogsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogsException(Throwable cause) {
        super(cause);
    }

    public LogsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
