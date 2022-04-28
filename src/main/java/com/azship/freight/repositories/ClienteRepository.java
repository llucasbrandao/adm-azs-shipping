package com.azship.freight.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.azship.freight.models.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
	
	@Query(value = "SELECT * FROM CLIENTE c WHERE CPF = :cpfCnpj OR CNPJ = :cpfCnpj", nativeQuery = true)
	Page<ClienteModel> findByCpfOrCnpj(String cpfCnpj, Pageable pageable);
	
	@Query(value = "SELECT * FROM CLIENTE c WHERE CNPJ = :cnpj", nativeQuery = true)
	Optional<ClienteModel> findByCnpj(String cnpj);
	
	@Query(value = "SELECT * FROM CLIENTE c WHERE CPF = :cpf", nativeQuery = true)
	Optional<ClienteModel> findByCpf(String cpf);
	
	@Query(value = "SELECT * FROM CLIENTE c WHERE RAZAO_SOCIAL= :razaoSocial", nativeQuery = true)
	Page<ClienteModel> findByRazaoSocial(String razaoSocial, Pageable pageable);
	
	@Query(value = "SELECT * FROM CLIENTE c WHERE NOME_FANTASIA = :nomeFantasia", nativeQuery = true)
	Page<ClienteModel> findByNomeFantasia(String nomeFantasia, Pageable pageable);
	
	@Query(value = "SELECT * FROM CLIENTE c WHERE NOME_PESSOA = :nomePessoa", nativeQuery = true)
	Page<ClienteModel> findByNomePessoa(String nomePessoa, Pageable pageable);
}
