package com.azship.freight.utils;

public class NumberUtils {
	
	public static Integer toPositiveInteger(String s) {
		try {
			return Integer.parseInt(s);
			
		} catch (NumberFormatException e) {
			throw new RuntimeException("Não foi possível converter a String '" + s + "' para Integer.");
		}
	}
	
	public static Integer toPositiveIntegerWithoutException(String s) {
		try {
			return Integer.parseInt(s);
			
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public static Long toPositiveLongWithException(String s) {
		try {
			return Long.parseLong(s);
			
		} catch (NumberFormatException e) {
			throw new RuntimeException("Não foi possível converter a String '" + s + "' para Long.");
		}
	}
	
	public static Long toPositiveLongWithoutException(String s) {
		try {
			return Long.parseLong(s);
			
		} catch (NumberFormatException e) {
			return Integer.valueOf(-1).longValue();
		}
	}
	
	public static Double toDoubleWithoutException(String s) {
		try {
			return Double.parseDouble(s);
			
		} catch (NumberFormatException e) {
			return Double.valueOf(-1);
		}
	}
	
	public static Double toDoubleWithException(String s) {
		try {
			return Double.parseDouble(s);
			
		} catch (NumberFormatException e) {
			throw new RuntimeException("Não foi possível converter a String '" + s + "' para Double.");
		}
	}
}
