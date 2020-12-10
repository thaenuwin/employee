package com.drsoft.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.drsoft.employee.config.ErrorCode;
import com.drsoft.employee.config.Response;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Response> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(Response.createErrorResponse(ErrorCode.ERROR_VALIDATIONS.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AbstractApplicationException.class)
    public final ResponseEntity<Response> handleAllExceptions(AbstractApplicationException e) {
        return new ResponseEntity<>(Response.createErrorResponse(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<Response> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(Response.createErrorResponse(e.getCode(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
