package com.restApi.socialMediaApi.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request){
		ErrorDetails errDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request){
		ErrorDetails errDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request){
		String nl = System.lineSeparator();
		String[] errMsg = {"Total Errors Count: "+ex.getErrorCount()
		+nl+"Below are the errors: "+nl};
		ex.getFieldErrors().forEach(e->{
			errMsg[0]+=e.getDefaultMessage()+", ";
		});
		
		ErrorDetails errDetails = new ErrorDetails(LocalDate.now(), 
				//"Total Errors Count: "+ex.getErrorCount()+ "Below are the errors: \n"+
				// ex.getFieldError().getDefaultMessage(),
				errMsg[0],
				request.getDescription(false));

		return new ResponseEntity<Object>(errDetails, HttpStatus.BAD_REQUEST);
	}
}
