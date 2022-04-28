package com.azship.freight.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONUtils {
	
	protected static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> String toJSON(T obj) {
		try {		
			return mapper.writeValueAsString(obj);
			
		} catch (Exception e) {
			log.error("Falha ao converter para JSON:");
			log.error(e.getLocalizedMessage());
		}
		
		return null;
	}
	
	public static <T> Object toJSON(T obj, Class<?> clazz) {
		try {		
			return mapper.convertValue(obj, clazz);
			
		} catch (Exception e) {
			log.error("Falha ao converter para JSON:");
			log.error(e.getLocalizedMessage());
		}
		
		return null;
	}
}
