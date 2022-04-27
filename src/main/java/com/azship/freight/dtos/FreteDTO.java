package com.azship.freight.dtos;

import java.math.BigDecimal;

import com.azship.freight.enums.MoedaFreteEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_EMPTY)
public class FreteDTO {
	
	private Long id;
	
	private ClienteDTO cliente;
	
	private String nomeProduto;
	
	private String descricaoProduto;
	
	private String sku;
	
	private String codigoExterno;
	
	private Double pesoKg;
	
	private Double cubagem;
	
	private String paisOrigem;
	
	private String paisDestino;
	
	private String estadoOrigem;
	
	private String estadoDestino;
	
	private String cepDestino;
	
	private String enderecoDestino;
	
	private BigDecimal valorFrete;
	
	private MoedaFreteEnum moedaFrete;
}
