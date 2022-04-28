package com.azship.freight.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENTE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicUpdate
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@Data
public class ClienteModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "RAZAO_SOCIAL")
	@ColumnTransformer(write = "UPPER(?)")
	private String razaoSocial;
	
	@Column(name = "NOME_FANTASIA")
	@ColumnTransformer(write = "UPPER(?)")
	private String nomeFantasia;
	
	private String cnpj;
	
	@Column(name = "CRIADO_EM")
	@CreationTimestamp
	private Date criadoEm;
	
	private String cpf;
	
	@Column(name = "NOME_PESSOA")
	@ColumnTransformer(write = "UPPER(?)")
	private String nomePessoa;
}
