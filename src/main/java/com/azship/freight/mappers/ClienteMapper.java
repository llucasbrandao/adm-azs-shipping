package com.azship.freight.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.azship.freight.dtos.ClienteDTO;
import com.azship.freight.models.ClienteModel;

@Mapper(
	componentModel = "spring", 
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClienteMapper {
	
	ClienteDTO toDto(ClienteModel model);
	
	ClienteModel toModel(ClienteDTO dto);
	
	List<ClienteDTO> toDTOList(List<ClienteModel> modelList);
}
