package com.drsoft.employee.exception;

public class EntityNotFoundException extends AbstractApplicationException {

    public EntityNotFoundException(Long code, String description) {
        super(code, description);
    }
}
