package com.drsoft.employee.exception;

public class AlreadyExistException extends AbstractApplicationException{
	
	 public AlreadyExistException(Long code, String description) {
	        super(code, description);
	    }
}
