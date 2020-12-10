package com.drsoft.employee.exception;

public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(Long code , String description) {
        super(code, description);
    }
}
