package com.azship.freight.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Column(name = "NOME_PRODUTO")
	@ColumnTransformer(write = "UPPER(?)")
	private String nomeProduto;
	
	@Column(name = "DESCRICAO_PRODUTO")
	private String descricaoProduto;
	
	private String sku;
	
	@Column(name = "CODIGO_EXTERNO")
	private String codigoExterno;
	
	@Column(name = "PESO_KG")
	private Double pesoKg;
	
	private Double cubagem;
	
	@Column(name = "PAIS_ORIGEM")
	@ColumnTransformer(write = "UPPER(?)")
	private String paisOrigem;
	
	@Column(name = "PAIS_DESTINO")
	@ColumnTransformer(write = "UPPER(?)")
	private String paisDestino;
	
	@Column(name = "ESTADO_ORIGEM")
	@ColumnTransformer(write = "UPPER(?)")
	private String estadoOrigem;
	
	@Column(name = "ESTADO_DESTINO")
	@ColumnTransformer(write = "UPPER(?)")
	private String estadoDestino;
	
	@Column(name = "CEP_DESTINO")
	private String cepDestino;
	
	@Column(name = "ENDERECO_DESTINO")
	@ColumnTransformer(write = "UPPER(?)")
	private String enderecoDestino;
	
	@Column(name = "VALOR_FRETE")
	private BigDecimal valorFrete;
	
	@Column(name = "MOEDA_FRETE")
	@Enumerated(EnumType.STRING)
	private MoedaFreteEnum moedaFrete;
	
	@Column(name = "CRIADO_EM")
	@CreationTimestamp
	private Date criadoEm;
	
	@Column(name = "DATA_ESTIMADA_ENTREGA")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataEstimadaEntrega;
}
