package com.springrest.springrest.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {

	private String status;
	private String errorCode;
	private String message;
	public ErrorResponseDTO(String status, String errorCode, String message) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}
	
	
}
