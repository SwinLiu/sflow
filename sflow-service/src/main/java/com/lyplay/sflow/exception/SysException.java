package com.lyplay.sflow.exception;


public class SysException extends RuntimeException {

	private static final long serialVersionUID = -8478652423029273534L;

	public SysException() {
		super();
	}

	public SysException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
	}

	public SysException(String message) {
		super(message);
	}

	public SysException(Throwable cause) {
		super(cause);
	}
	
	
}
