package com.sunil.workx.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1530806493433672760L;
	
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
