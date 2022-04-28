package com.azship.freight.utils;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
	
	public static String toUpperCase(String str) {
		return str != null ? str.toUpperCase() : "";
	}
	
	public static String randomString(Integer length) {
		char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
		
		return RandomStringUtils.random(length, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
	}
}
