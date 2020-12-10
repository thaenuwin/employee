package com.drsoft.employee.exception;

public class PasswordWrongException extends AuthenticationException {
	public PasswordWrongException(Long code, String description) {
		super(code, description);
	}
}
