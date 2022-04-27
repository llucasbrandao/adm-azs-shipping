package com.azship.freight.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azship.freight.dtos.ClienteDTO;
import com.azship.freight.services.impl.ClienteServiceImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
@RequestMapping("api")	
@Tag(name = "API AzShip")
@OpenAPIDefinition(info = @Info(title = "API", description = "API de controle de fretes", version = "1.0"))
public class ClienteController {
	
	private final ClienteServiceImpl clienteServiceImpl;
	
	@PostMapping("/novo-cliente")
	public ResponseEntity<ClienteDTO> novoCliente(@RequestBody ClienteDTO dto) {
		return new ResponseEntity<ClienteDTO>(clienteServiceImpl.novoCliente(dto), 
				HttpStatus.CREATED);
	}
}
