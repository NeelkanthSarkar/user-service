package com.demoapp.exceptionsmapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class UserValidationExceptionHandling {
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleUserEntityException(MethodArgumentNotValidException ex){
		  
    	Map<String,String> resultMap = new HashMap<>();
    	ex.getBindingResult().getFieldErrors().forEach(er->{
    		 resultMap.put(er.getField(), er.getDefaultMessage());
    	});
    	return resultMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserByIdNotFoundException.class)
	public Map<String,String> handleUserByIdNotFoundException(UserByIdNotFoundException ex){
		
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("errorMessage",ex.getMessage());
		return resultMap;
	}
}
