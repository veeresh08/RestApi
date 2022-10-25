package com.springrest.springrest.exception;

public class DepartmentNotFouncException extends Exception {
	 	
		public DepartmentNotFouncException() {
	        super();
	    }

	    public DepartmentNotFouncException(String message) {
	        super(message);
	    }

	    public DepartmentNotFouncException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public DepartmentNotFouncException(Throwable cause) {
	        super(cause);
	    }

	    protected DepartmentNotFouncException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
}
