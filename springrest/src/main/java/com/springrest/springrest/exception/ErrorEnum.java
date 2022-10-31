package com.springrest.springrest.exception;

public enum ErrorEnum {
	

	    FORBIDDEN("403", "Forbidden."),
		USER_DONT_ACCESS("100", "User don't have access."),
		METHOD_LOGIC_ERROR("101", "An error occurred while executing the Logic"),
		COURSE_NOT_FOUND("400","NOT FOUND"),
		DEPARTMENT_NOT_FOUND("400","Department not found"),
		STUDENT_NOT_FOUND("404","Student not found");

		private final String errorCode;
	
		private final String message;

		ErrorEnum(String errorCode, String message) {
			this.errorCode = errorCode;
			this.message = message;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public String getMessage() {
			return message;
		}

	
}
