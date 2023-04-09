package com.sunil.workx.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -1530806493433672761L;
	
	
	public ResourceAlreadyExistsException(String message) {
		super(message);
	}

}
