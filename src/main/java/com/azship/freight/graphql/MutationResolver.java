package com.azship.freight.graphql;

import org.springframework.stereotype.Component;

import com.azship.freight.dtos.ClienteDTO;
import com.azship.freight.dtos.FreteDTO;
import com.azship.freight.exceptions.GenericException;
import com.azship.freight.exceptions.GraphQLException;
import com.azship.freight.services.impl.ClienteServiceImpl;
import com.azship.freight.services.impl.FreteServiceImpl;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {
	
	private final ClienteServiceImpl clienteServiceImpl;
	
	private final FreteServiceImpl freteServiceImpl;
	
	public ClienteDTO cadastraCliente(ClienteDTO cliente) {
		try {
			return clienteServiceImpl.novoCliente(cliente);
			
		} catch (GenericException e) {
			if (e.getErrors() != null && e.getErrors().size() > 0) {
				throw new GraphQLException(e.getErrors().toString(), null);
				
			} else {
				throw new GraphQLException(e.getMessage(), null);
			}
		}
	}
	
	public FreteDTO cadastraFrete(FreteDTO frete) {
		try {
			return freteServiceImpl.novoFrete(frete);
			
		} catch (GenericException e) {
			if (e.getErrors() != null && e.getErrors().size() > 0) {
				throw new GraphQLException(e.getErrors().toString(), null);
				
			} else {
				throw new GraphQLException(e.getMessage(), null);
			}
		}
	}
}
