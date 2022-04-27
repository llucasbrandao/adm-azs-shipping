package com.azship.freight.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class DuplicateItemException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
}
