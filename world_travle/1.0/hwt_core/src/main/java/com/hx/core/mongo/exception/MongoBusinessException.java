package com.hx.core.mongo.exception;


public class MongoBusinessException extends Exception {

	private static final long serialVersionUID = -8959279722808319898L;

	public MongoBusinessException() {
        super();
    }

    public MongoBusinessException(String message) {
        super(message);
    }

    public MongoBusinessException(Throwable cause) {
        super(cause);
    }

    public MongoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
