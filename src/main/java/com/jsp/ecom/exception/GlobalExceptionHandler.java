
package com.jsp.ecom.exception;

import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> handle(BadCredentialsException exception) {
		return Map.of("error", exception.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handle(IllegalArgumentException exception) {
		return Map.of("error", exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handle(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new LinkedHashMap<String, String>();
		exception.getBindingResult().getFieldErrors().forEach(x -> errors.put(x.getField(), x.getDefaultMessage()));
		return Map.of("error", errors);
	}
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, Object> handle(AccessDeniedException exception) {
		return Map.of("error", "You are Not Allowed to Access This Endpoint");
	}

	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> handle(NoResourceFoundException exception) {
		return Map.of("error", "You have Enterd Wrong URL");
	}

}
