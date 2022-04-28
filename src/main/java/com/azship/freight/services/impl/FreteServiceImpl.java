package com.azship.freight.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Holder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.azship.freight.dtos.FreteDTO;
import com.azship.freight.dtos.PageDTO;
import com.azship.freight.enums.ErrorsEnum;
import com.azship.freight.exceptions.DBException;
import com.azship.freight.exceptions.GenericException;
import com.azship.freight.mappers.FreteMapper;
import com.azship.freight.models.FreteModel;
import com.azship.freight.repositories.FreteRepository;
import com.azship.freight.services.interfaces.FreteServiceInterface;
import com.azship.freight.utils.PageableUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreteServiceImpl implements FreteServiceInterface {
	
	private final ClienteServiceImpl clienteServiceImpl;
	
	private final FreteRepository freteRepository;
	
	private final FreteMapper freteMapper;
	
	@Override
	public FreteDTO novoFrete(FreteDTO freteDTO) {
		if (freteDTO != null && freteDTO.getCliente() != null) {
			FreteModel freteModel = new FreteModel();
			
			freteModel = freteMapper.toModel(freteDTO);
			
			freteModel.setId(null);
			
			freteModel = this.validations(freteModel);
			
			try {
				return freteMapper.toDto(freteRepository.save(freteModel));
				
			} catch (Exception e) {
				throw new DBException("Ocorreu uma falha ao salvar os dados. Por favor, contate o suporte.", 
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		throw new GenericException("O objeto não pode estar nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public PageDTO<FreteDTO> buscaFrete(Long id, Long clienteId, String cnpj, Pageable pageable) {
		Map<ErrorsEnum, String> errors = new HashMap<ErrorsEnum, String>();
		Holder<PageDTO<FreteDTO>> pageDTO = new Holder<>();
		Holder<Page<FreteModel>> page = new Holder<>();
		
		if (id == null && clienteId == null && cnpj == null) {
			errors.put(ErrorsEnum.PARAMETROS_NULOS, "Nenhum parâmetro de busca foi informado.");
		} else {
			if (id != null) {
				freteRepository.findById(id).ifPresentOrElse(frete -> {
					List<FreteDTO> lista = new ArrayList<>();
					
					lista.add(freteMapper.toDto(frete));
					
					pageDTO.value = PageableUtils.generatePageDTO(lista, Integer.valueOf(lista.size()).longValue(), 
							1, 1, 1); 
					
				}, () -> {
					errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum item foi encontrado com o ID " + id + ".");
				});
				
			} else if (clienteId != null) {
				clienteServiceImpl.buscaCliente(clienteId, null, null).ifPresentOrElse(cliente -> {
					page.value = freteRepository.findByCliente(cliente, pageable);
					
					if (page.value.getContent() == null || page.value.getContent().size() == 0) {
						errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum frete foi encontrado para o cliente informado.");
						
					} else {
						pageDTO.value = PageableUtils.generatePageDTO(freteMapper.toDTOList(page.value.getContent()), page.value.getTotalElements(), 
								page.value.getTotalPages(), page.value.getNumber(), page.value.getSize());
					}
					
				}, () -> {
					errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum cliente foi encontrado com o ID " + clienteId + ".");
				});
				
			} else if (cnpj != null) {
				clienteServiceImpl.buscaCliente(null, cnpj, null).ifPresentOrElse(cliente -> {
					page.value = freteRepository.findByCliente(cliente, pageable);
					
					if (page.value.getContent() == null || page.value.getContent().size() == 0) {
						errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum frete foi encontrado para o cliente informado.");
						
					} else {
						pageDTO.value = PageableUtils.generatePageDTO(freteMapper.toDTOList(page.value.getContent()), page.value.getTotalElements(), 
								page.value.getTotalPages(), page.value.getNumber(), page.value.getSize());
					}
					
				}, () -> {
					errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum cliente foi encontrado com o CNPJ " + cnpj + ".");
				});
			} 
		}
		
		this.throwExceptions(errors);
		
		return pageDTO.value;
	}
	
	private FreteModel validations(FreteModel model) {
		Map<ErrorsEnum, String> errors = new HashMap<>();
		
		if (model != null && model.getCliente() != null 
				&& model.getCliente().getId() != null) {
			
			clienteServiceImpl.buscaCliente(model.getCliente().getId(), null, null).ifPresentOrElse(cliente -> {
				model.setCliente(cliente);
				
				if (model.getCepDestino() == null || model.getCepDestino().isBlank()) {
					errors.put(ErrorsEnum.PARAMETROS_NULOS, "O CEP não pode estar em branco.");
				}
				
			}, () -> {
				errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum cliente foi encontrado com o ID " + model.getCliente().getId() + ".");
			});
		}
		
		this.throwExceptions(errors);
		
		return model;
	}
	
	private void throwExceptions(Map<ErrorsEnum, String> errors) {
		for (ErrorsEnum key : errors.keySet()) {
			if (key.equals(ErrorsEnum.ITEM_NAO_ENCONTRADO)) {
				throw new GenericException(errors, HttpStatus.NOT_FOUND);
			}
			
			if (key.equals(ErrorsEnum.ITEM_DUPLICADO)) {
				throw new GenericException(errors, HttpStatus.CONFLICT);
			}
		}
		
		if (errors.size() > 0) {
			throw new GenericException(errors, HttpStatus.BAD_REQUEST);
		}
	}
}
