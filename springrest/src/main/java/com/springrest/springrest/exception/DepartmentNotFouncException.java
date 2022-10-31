package com.springrest.springrest.exception;

import org.springframework.http.HttpStatus;

public class DepartmentNotFouncException extends Exception {
	 	
	private static final long serialVersionUID = 1L;

	private final ErrorEnum error;

	private final HttpStatus httpStatus;

	public ErrorEnum getError() {
		return error;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public DepartmentNotFouncException(ErrorEnum error, HttpStatus httpStatus) {
		this.error = error;
		this.httpStatus = httpStatus;
	}
	
	
//	private static final long serialVersionUID = 1L;
//
//		public DepartmentNotFouncException() {
//	        super();
//	    }
//
//	    public DepartmentNotFouncException(String message) {
//	        super(message);
//	    }
//
//	    public DepartmentNotFouncException(String message, Throwable cause) {
//	        super(message, cause);
//	    }
//
//	    public DepartmentNotFouncException(Throwable cause) {
//	        super(cause);
//	    }
//
//	    protected DepartmentNotFouncException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//	        super(message, cause, enableSuppression, writableStackTrace);
//	    }
	    
	    
}
