package com.azship.freight.exceptions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GenericException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private Map<String, String> errors;
	
	private String message;
	
	private HttpStatus httpCode;
	
	public GenericException(String message, HttpStatus statusCode) {
		this.message = message;
		this.httpCode = statusCode;
	}
	
	public GenericException(Map<String, String> errors, HttpStatus statusCode) {
		this.errors = errors;
		this.httpCode = statusCode;
	}
}