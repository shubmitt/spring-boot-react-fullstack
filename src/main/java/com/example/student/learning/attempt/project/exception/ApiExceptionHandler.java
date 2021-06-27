package com.example.student.learning.attempt.project.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler{
	
	@ExceptionHandler(ApiRequestException.class)
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(e.getMessage(),HttpStatus.BAD_REQUEST,ZonedDateTime.now(ZoneId.of("Z")));
		
		return new ResponseEntity<>(apiException,badRequest);
	}
	
	
}