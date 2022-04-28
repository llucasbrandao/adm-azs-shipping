package com.azship.freight.graphql;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.azship.freight.dtos.ClienteDTO;
import com.azship.freight.dtos.FreteDTO;
import com.azship.freight.exceptions.GenericException;
import com.azship.freight.exceptions.GraphQLException;
import com.azship.freight.mappers.ClienteMapper;
import com.azship.freight.services.impl.ClienteServiceImpl;
import com.azship.freight.services.impl.FreteServiceImpl;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {
	
	private final ClienteServiceImpl clienteServiceImpl;
	
	private final FreteServiceImpl freteServiceImpl;
	
	private final ClienteMapper clienteMapper;
	
	public ClienteDTO buscaCliente(Long id, String cnpj, String cpf, Integer size, Integer page) {
		if (id != null) {
			return clienteMapper.toDto(clienteServiceImpl.buscaCliente(id, null, null).orElseThrow(() -> {
				throw new GraphQLException("Nenhum cliente foi encontrado com os parâmetros informados.", null);
			}));
		}
		
		if (cnpj != null && !cnpj.isBlank()) {
			return clienteMapper.toDto(clienteServiceImpl.buscaCliente(null, cnpj, null).orElseThrow(() -> {
				throw new GraphQLException("Nenhum cliente foi encontrado com os parâmetros informados.", null);
			}));
		}
		
		if (cpf != null && !cpf.isBlank()) {
			return clienteMapper.toDto(clienteServiceImpl.buscaCliente(null, null, cpf).orElseThrow(() -> {
				throw new GraphQLException("Nenhum cliente foi encontrado com os parâmetros informados.", null);
			}));
		}
		
		throw new GraphQLException("É preciso informar o ID ou CNPJ do cliente", null);
	}
	
	public List<FreteDTO> buscaFrete(Long id, Long clienteId, String cnpj, Integer size, Integer page) {
		try {
			return freteServiceImpl.buscaFrete(id, clienteId, cnpj, 
					PageRequest.of(page == 0 ? page : page - 1, size)).getContent();
			
		} catch (GenericException e) {
			if (e.getErrors() != null && e.getErrors().size() > 0) {
				throw new GraphQLException(e.getErrors().toString(), "");
			}
			
			throw new GraphQLException(e.getLocalizedMessage(), "");
		}
	}
}
