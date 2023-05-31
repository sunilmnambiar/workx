package com.sunil.workx.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -1530806493433672760L;
	
	
	public BadRequestException(String message) {
		super(message);
	}

}
