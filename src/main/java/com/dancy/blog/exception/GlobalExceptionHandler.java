package com.dancy.blog.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dancy.blog.payloads.ApiResponses;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponses> resourceNotFoundException(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponses apiResponses = new ApiResponses(message,false);
		return new ResponseEntity<ApiResponses>(apiResponses,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		HashMap<String,String> maps = new HashMap<String,String>();
	ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldName = ((FieldError)error).getField();
		String message = error.getDefaultMessage();
		maps.put(fieldName, message);
	});
		return new ResponseEntity<HashMap<String,String>>(maps,HttpStatus.OK);
		
	}

}
