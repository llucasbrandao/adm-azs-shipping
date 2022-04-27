package com.azship.freight.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicUpdate;

import com.azship.freight.enums.MoedaFreteEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FRETE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicUpdate
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@Data
public class FreteModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "CLIENTE_ID", referencedColumnName = "id")
	private ClienteModel cliente;
	
	@ColumnTransformer(write = "UPPER(?)")
	private String nomeProduto;
	
	private String descricaoProduto;
	
	private String sku;
	
	private String codigoExterno;
	
	private Double pesoKg;
	
	private Double cubagem;
	
	@ColumnTransformer(write = "UPPER(?)")
	private String paisOrigem;
	
	@ColumnTransformer(write = "UPPER(?)")
	private String paisDestino;
	
	@ColumnTransformer(write = "UPPER(?)")
	private String estadoOrigem;
	
	@ColumnTransformer(write = "UPPER(?)")
	private String estadoDestino;
	
	private String cepDestino;
	
	@ColumnTransformer(write = "UPPER(?)")
	private String enderecoDestino;
	
	private BigDecimal valorFrete;
	
	@Enumerated(EnumType.STRING)
	private MoedaFreteEnum moedaFrete;
}
