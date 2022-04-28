package com.azship.freight.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azship.freight.dtos.FreteDTO;
import com.azship.freight.dtos.PageDTO;
import com.azship.freight.services.impl.FreteServiceImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
@RequestMapping("api/frete")	
@Tag(name = "API AzShip")
@OpenAPIDefinition(info = @Info(title = "API", description = "API de controle de fretes", version = "1.0"))
public class FreightController {
	
	private final FreteServiceImpl freteServiceImpl;
	
	@Operation(
			summary = "Cadastra um novo frete.", 
			description = "É necessário informar apenas o ID, no campo 'cliente'."
	)
	@PostMapping("/novo")
	public ResponseEntity<FreteDTO> novoFrete(@RequestBody FreteDTO dto) {
		return new ResponseEntity<FreteDTO>(freteServiceImpl.novoFrete(dto), 
				HttpStatus.CREATED);
	}
	
	@GetMapping("/busca")
	public ResponseEntity<PageDTO<FreteDTO>> busca(@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long clienteId,
			@RequestParam(required = false) String cnpj, 
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size) {
		
		return new ResponseEntity<PageDTO<FreteDTO>>(freteServiceImpl.buscaFrete(id, clienteId, cnpj, 
				PageRequest.of(page == 0 ? page : page - 1, size)), 
				HttpStatus.OK);
	}
}
