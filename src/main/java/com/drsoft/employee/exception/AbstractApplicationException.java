package com.drsoft.employee.exception;

import lombok.Getter;

public abstract class AbstractApplicationException extends RuntimeException {

    @Getter
    private final Long code;

    public AbstractApplicationException(Long code, String description) {
        super(description);
        this.code = code;
    }
}
