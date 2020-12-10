package com.drsoft.employee.exception;

public class TokenExpiredException extends AuthenticationException {

    public TokenExpiredException(Long code, String description) {
        super(code, description);
    }
}
