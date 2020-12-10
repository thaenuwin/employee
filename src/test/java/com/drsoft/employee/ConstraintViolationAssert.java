package com.drsoft.employee;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.fail;


public final class ConstraintViolationAssert<T> {

    private final Set<ConstraintViolation<T>> violations;

    private ConstraintViolationAssert(Set<ConstraintViolation<T>> violations) {
        this.violations = violations;
    }

    public static <T> ConstraintViolationAssert<T> assertViolations(Set<ConstraintViolation<T>> violations) {
        return new ConstraintViolationAssert<>(violations);
    }

    public ConstraintViolationAssert<T> hasViolation(String fieldName, String message) {
        for (ConstraintViolation<?> v : violations) {
            if (v.getPropertyPath().toString().equals(fieldName) && v.getMessage().equals(message)) {
                return this;
            }
        }
        return fail(String.format("Should has violation on field '%s' with message '%s'.", fieldName, message));
    }
}
