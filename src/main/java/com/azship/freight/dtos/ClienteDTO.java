package com.azship.freight.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_EMPTY)
public class ClienteDTO {

	private Long id;
	
	private String razaoSocial;
	
	private String nomeFantasia;
	
	private String cnpj;
	
	private Date criadoEm;
	
	private String cpf;
	
	private String nomePessoa;
}
