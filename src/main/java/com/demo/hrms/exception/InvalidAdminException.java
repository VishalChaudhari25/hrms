package com.demo.hrms.exception;

public class InvalidAdminException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidAdminException(String message) {
	        super(message);
	    }
}
