package com.demo.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(EmployeeNotFoundException.class)
	    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(InvalidEmployeeException.class)
	    public ResponseEntity<String> handleInvalidEmployeeException(InvalidEmployeeException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    
	    
	    @ExceptionHandler(AdminNotFoundException.class)
	    public ResponseEntity<String> handleAdminNotFoundException(AdminNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(InvalidAdminException.class)
	    public ResponseEntity<String> handleInvalidAdminException(InvalidAdminException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(AttendanceNotFoundException.class)
	    public ResponseEntity<String> handleAttendanceNotFoundException(AttendanceNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(InvalidAttendanceException.class)
	    public ResponseEntity<String> handleInvalidAttendanceException(InvalidAttendanceException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGlobalException(Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    
   
}
