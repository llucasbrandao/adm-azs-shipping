package com.azship.freight.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.ws.Holder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.azship.freight.dtos.ClienteDTO;
import com.azship.freight.dtos.PageDTO;
import com.azship.freight.enums.ErrorsEnum;
import com.azship.freight.exceptions.GenericException;
import com.azship.freight.mappers.ClienteMapper;
import com.azship.freight.models.ClienteModel;
import com.azship.freight.repositories.ClienteRepository;
import com.azship.freight.services.interfaces.ClienteServiceInterface;
import com.azship.freight.utils.PageableUtils;

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
			
			clienteModel.setId(null);
			clienteModel.setCriadoEm(null);
			
			return clienteMapper.toDto(clienteRepository.save(this.validations(clienteModel)));
		}
		
		throw new GenericException("O CPF ou o CNPJ são obrigatórios.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public PageDTO<ClienteDTO> buscaCliente(Long id, String cpfCnpj, String nomePessoa, String razaoSocial,
			String nomeFantasia, Pageable pageable) {
		
		Map<ErrorsEnum, String> errors = new HashMap<ErrorsEnum, String>();
		Holder<PageDTO<ClienteDTO>> pageDTO = new Holder<>();
		Page<ClienteModel> page = null;
		
		if (id == null && cpfCnpj == null && nomePessoa == null && razaoSocial == null && nomeFantasia == null) {
			errors.put(ErrorsEnum.PARAMETROS_NULOS, "Nenhum parâmetro de busca foi informado.");
			
		} else {
			if (id != null) {
				clienteRepository.findById(id).ifPresentOrElse(item -> {
					List<ClienteDTO> lista = new ArrayList<>();
					
					lista.add(clienteMapper.toDto(item));
					
					pageDTO.value = PageableUtils.generatePageDTO(lista, Integer.valueOf(lista.size()).longValue(), 
							1, 1, 1); 
							
				}, () -> {
					errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum item foi encontrado com o ID " + id + ".");
				});
				
			} else if (cpfCnpj != null && !cpfCnpj.isBlank()) {
				page = clienteRepository.findByCpfOrCnpj(cpfCnpj, pageable);
				
				if (page.getContent() == null || page.getContent().size() == 0) {
					errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum item foi encontrado com o CPF/CNPJ: " + cpfCnpj + ".");
					
				} else {
					pageDTO.value = PageableUtils.generatePageDTO(clienteMapper.toDTOList(page.getContent()), page.getTotalElements(), 
							page.getTotalPages(), page.getNumber(), page.getSize());
				}
				
			} else if (nomePessoa != null && !nomePessoa.isBlank()) {
				page = clienteRepository.findByNomePessoa(nomePessoa, pageable);
				
				if (page.getContent() == null || page.getContent().size() == 0) {
					errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum item foi encontrado com o nomePessoa: " + nomePessoa + ".");
					
				} else {
					pageDTO.value = PageableUtils.generatePageDTO(clienteMapper.toDTOList(page.getContent()), page.getTotalElements(), 
							page.getTotalPages(), page.getNumber(), page.getSize());
				}
				
			} else if (razaoSocial != null && !razaoSocial.isBlank()) {
				page = clienteRepository.findByNomePessoa(razaoSocial, pageable);
				
				if (page.getContent() == null || page.getContent().size() == 0) {
					errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum item foi encontrado com a razaoSocial: " + razaoSocial + ".");
					
				} else {
					pageDTO.value = PageableUtils.generatePageDTO(clienteMapper.toDTOList(page.getContent()), page.getTotalElements(), 
							page.getTotalPages(), page.getNumber(), page.getSize());
				}
				
			} else if (nomeFantasia != null && !nomeFantasia.isBlank()) {
				page = clienteRepository.findByNomePessoa(nomeFantasia, pageable);
				
				if (page.getContent() == null || page.getContent().size() == 0) {
					errors.put(ErrorsEnum.ITEM_NAO_ENCONTRADO, "Nenhum item foi encontrado com o nomeFantasia: " + nomeFantasia + ".");
					
				} else {
					pageDTO.value = PageableUtils.generatePageDTO(clienteMapper.toDTOList(page.getContent()), page.getTotalElements(), 
							page.getTotalPages(), page.getNumber(), page.getSize());
				}
			}
		}
		
		if (errors.size() > 0) {
			throw new GenericException(errors, HttpStatus.NOT_FOUND);
		}
		
		return pageDTO.value;
	}
	
	@Override
	public Optional<ClienteModel> buscaCliente(Long id, String cnpj, String cpf) {
		if (id != null) {
			return clienteRepository.findById(id);
		}
		
		if (cnpj != null) {
			return clienteRepository.findByCnpj(cnpj);
		}
		
		if (cpf != null) {
			return clienteRepository.findByCpf(cpf);
		}
		
		return null;
	}
	
	private ClienteModel validations(ClienteModel model) {
		Map<ErrorsEnum, String> errors = new HashMap<>();
		
		if (model == null) {
			errors.put(ErrorsEnum.PARAMETROS_NULOS, "Dados em branco.");
			
		} else {
			if (model.getCnpj() != null) {
				if (model.getCnpj().length() == 14) {
					if (model.getRazaoSocial() == null || model.getRazaoSocial().length() < RAZAO_SOCIAL_MIN_LENGTH) {
						errors.put(ErrorsEnum.RAZAO_SOCIAL_LENGTH_EXCEEDED, 
								"A razão social não pode ter menos que " + RAZAO_SOCIAL_MIN_LENGTH + " caracteres.");
						
					} else {
						clienteRepository.findByCnpj(model.getCnpj()).ifPresentOrElse(item -> {
							errors.put(ErrorsEnum.ITEM_DUPLICADO, "Já existe um cliente com o CNPJ informado.");
							
						}, () -> {
							model.setNomePessoa(null);
						});
					}
					
				} else {
					errors.put(ErrorsEnum.CNPJ_LENGTH_EXCEEDED, "O CNPJ deve ter 14 dígitos.");
				}
			}
			
			if (model.getCpf() != null) {
				if (model.getCpf().length() == 11) {
					if (model.getNomePessoa() == null || model.getNomePessoa().length() < NOME_PESSOA_MIN_LENGTH) {
						errors.put(ErrorsEnum.NOME_PESSOA_LENGTH_EXCEEDED, 
								"O nome da pessoa não pode ter menos que + " + NOME_PESSOA_MIN_LENGTH + " caracteres.");
						
					} else {
						clienteRepository.findByCpf(model.getCpf()).ifPresentOrElse(item -> {
							errors.put(ErrorsEnum.ITEM_DUPLICADO, "Já existe um cliente com o CPF informado.");
							
						}, () -> {
							model.setNomeFantasia(null);
							model.setRazaoSocial(null);
						});
					}
					
				} else {
					errors.put(ErrorsEnum.CPF_LENGTH_EXCEEDED, "O CPF deve ter 11 dígitos.");
				}
			}
		}
		
		this.throwExceptions(errors);
		
		return model;
	}
	
	private void throwExceptions(Map<ErrorsEnum, String> errors) {
		if (errors != null && errors.size() > 0) {
			if ((errors.containsKey(ErrorsEnum.RAZAO_SOCIAL_LENGTH_EXCEEDED) 
					|| errors.containsKey(ErrorsEnum.CNPJ_LENGTH_EXCEEDED)) 
				&& (errors.containsKey(ErrorsEnum.NOME_PESSOA_LENGTH_EXCEEDED) 
						|| errors.containsKey(ErrorsEnum.CPF_LENGTH_EXCEEDED))) {
			
				throw new GenericException("Não é permitido cadastrar um CPF e CNPJ ao mesmo tempo.", HttpStatus.CONFLICT);
			}
			
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
}
