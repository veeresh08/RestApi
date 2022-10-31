package com.springrest.springrest.exception;

import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	//	private final ErrorEnum error;
//
//	private final HttpStatus httpStatus;
//	
	public CourseNotFoundException(String message) {
		super(message);
	}

//	public CourseNotFoundException(ErrorEnum error, HttpStatus httpStatus) {
//		this.error = error;
//		this.httpStatus = httpStatus;
//	}
}
