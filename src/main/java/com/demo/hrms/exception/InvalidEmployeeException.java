package com.demo.hrms.exception;

public class InvalidEmployeeException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public InvalidEmployeeException(String message) {
        super(message);
    }

}
