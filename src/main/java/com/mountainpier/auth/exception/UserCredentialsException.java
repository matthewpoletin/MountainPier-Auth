package com.mountainpier.auth.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserCredentialsException extends Exception {
	
	public UserCredentialsException(String message) {
		super(message);
	}
	
}
