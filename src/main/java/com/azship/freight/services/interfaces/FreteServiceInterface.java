package com.azship.freight.services.interfaces;

import org.springframework.data.domain.Pageable;

import com.azship.freight.dtos.FreteDTO;
import com.azship.freight.dtos.PageDTO;

public interface FreteServiceInterface {
	
	FreteDTO novoFrete(FreteDTO freteDTO);
	
	PageDTO<FreteDTO> buscaFrete(Long id, Long clienteId, String cnpj, Pageable pageable);
}