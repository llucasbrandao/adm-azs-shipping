package com.azship.freight.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.azship.freight.dtos.ClienteDTO;
import com.azship.freight.exceptions.GenericException;
import com.azship.freight.mappers.ClienteMapper;
import com.azship.freight.models.ClienteModel;
import com.azship.freight.repositories.ClienteRepository;
import com.azship.freight.services.interfaces.ClienteServiceInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteServiceInterface {
	
	private final ClienteRepository clienteRepository;
	
	private final ClienteMapper clienteMapper;
	
	private static final int RAZAO_SOCIAL_MIN_LENGTH = 5;
	
	private static final int NOME_PESSOA_MIN_LENGTH = 5;
	
	@Override
	public ClienteDTO novoCliente(ClienteDTO clienteDTO) {
		if (clienteDTO != null && 
				(clienteDTO.getCnpj() != null && !clienteDTO.getCnpj().isBlank()) 
					|| (clienteDTO.getCpf() != null && !clienteDTO.getCpf().isBlank())) {
			
			ClienteModel clienteModel = clienteMapper.toModel(clienteDTO);
			
			this.validations(clienteModel);
			
			return clienteMapper.toDto(clienteRepository.save(clienteModel));
		}
		
		throw new GenericException("O objeto não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	private void validations(ClienteModel model) {
		List<String> errors = new ArrayList<>();
		
		if (model == null) {
			errors.add("Dados em branco.");
			
		} else {
			if (model.getCnpj() != null && model.getCnpj().length() == 14) {
				if (model.getRazaoSocial() == null || model.getRazaoSocial().length() < RAZAO_SOCIAL_MIN_LENGTH) {
					errors.add("A razão social não pode ter menos que " + RAZAO_SOCIAL_MIN_LENGTH + " caracteres.");
				}
			}
			
			if (model.getCpf() != null && model.getCpf().length() == 11) {
				if (model.getNomePessoa() == null || model.getNomePessoa().length() < NOME_PESSOA_MIN_LENGTH) {
					errors.add("O nome da pessoa não pode ter menos que + " + NOME_PESSOA_MIN_LENGTH + " caracteres.");
				}
			}
		}
		
		throw new GenericException(errors, HttpStatus.BAD_REQUEST);
	}
}
