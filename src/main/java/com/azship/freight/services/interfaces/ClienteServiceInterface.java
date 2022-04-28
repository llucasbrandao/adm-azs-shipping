package com.azship.freight.services.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.azship.freight.dtos.ClienteDTO;
import com.azship.freight.dtos.PageDTO;
import com.azship.freight.models.ClienteModel;

public interface ClienteServiceInterface {
	
	ClienteDTO novoCliente(ClienteDTO clienteDTO);
	
	PageDTO<ClienteDTO> buscaCliente(Long id, String cpfCnpj, String nomePessoa, 
			String razaoSocial, String nomeFantasia, Pageable pageable);
	
	Optional<ClienteModel> buscaCliente(Long id, String cnpj, String cpf);
}
