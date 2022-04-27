package com.azship.freight.exceptions;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class ExceptionModel {
	
	private String mensagem;
	
	private String url;
	
	private Date timestamp;
	
	private String method;
	
	private String sistema;
	
	private HttpStatus httpStatus;
	
	private List<String> errors;
	
	public ExceptionModel(String msg, String url, Date timestamp, 
			String method, String sistema) {
		
		this.mensagem = msg;
		this.url = url;
		this.timestamp = timestamp;
		this.method = method;
		this.sistema = sistema;
	}
	
	public ExceptionModel(String msg, String url, Date timestamp, 
			String method, String sistema, HttpStatus httpStatus) {
		
		this.mensagem = msg;
		this.url = url;
		this.timestamp = timestamp;
		this.method = method;
		this.sistema = sistema;
		this.httpStatus = httpStatus;
	}
	
	public ExceptionModel(List<String> errors, String url, Date timestamp, 
			String method, String sistema) {
		
		this.errors = errors;
		this.url = url;
		this.timestamp = timestamp;
		this.method = method;
		this.sistema = sistema;
	}
}
