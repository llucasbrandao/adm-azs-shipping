package com.azship.freight.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DBException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private String message;
	
	private HttpStatus httpCode;
}
