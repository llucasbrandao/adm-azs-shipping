package com.azship.freight.enums;

public enum ErrorsEnum {
	RAZAO_SOCIAL_LENGTH_EXCEEDED("2"),
	CNPJ_LENGTH_EXCEEDED("3"),
	NOME_PESSOA_LENGTH_EXCEEDED("4"),
	CPF_LENGTH_EXCEEDED("5"),
	ITEM_NAO_ENCONTRADO("10"),
	PARAMETROS_NULOS("11"),
	ITEM_DUPLICADO("12");
	
	private String code;
	
	ErrorsEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
