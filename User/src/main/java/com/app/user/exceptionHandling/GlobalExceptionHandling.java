package com.app.user.exceptionHandling;

import java.util.Date;



import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;



@ControllerAdvice

public class GlobalExceptionHandling {



	// handling specific exception

	@ExceptionHandler(ResourceNotFoundException.class)

	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){

		ErrorDetails errorDetails = 

				new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(APIException.class)

	public ResponseEntity<?> resourceNotFoundHandling(APIException exception, WebRequest request){

		ErrorDetails errorDetails = 

				new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

	}



	// handling global exception

	

	@ExceptionHandler(Exception.class)

	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){

		ErrorDetails errorDetails = 

				new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	@ExceptionHandler(MethodArgumentNotValidException.class)

	public ResponseEntity<?> customValidtionException(MethodArgumentNotValidException exception){

		ErrorDetails errorDetails = 

				new ErrorDetails(new Date(), "validation Error", exception.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

	}

}