package com.drsoft.employee.exception;

import lombok.Getter;

public class AuthenticationException extends RuntimeException {

    @Getter
    private final Long code;

    public AuthenticationException(Long code, String description) {
        super(description);
        this.code = code;
    }
}
