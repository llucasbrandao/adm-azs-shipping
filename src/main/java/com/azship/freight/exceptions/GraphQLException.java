package com.azship.freight.exceptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GraphQLException extends RuntimeException implements GraphQLError {
	
	private static final long serialVersionUID = 1L;
	
	private String invalidField;
	
	public GraphQLException(String message, String invalidField) {
		super(message);

		this.invalidField = invalidField;
	}

	@Override
	public ErrorType getErrorType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getMessage() {
       return super.getMessage();
   }

	@Override
	public List<SourceLocation> getLocations() {
       return null;
	}
	
	@Override
	public Map<String, Object> getExtensions() {
       return Collections.singletonMap("invalidField", invalidField);
	}
}
