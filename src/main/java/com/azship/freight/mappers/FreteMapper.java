package com.azship.freight.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.azship.freight.dtos.FreteDTO;
import com.azship.freight.models.FreteModel;

@Mapper(
	componentModel = "spring", 
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface FreteMapper {
	
	FreteDTO toDTO(FreteModel model);
	
	FreteModel toModel(FreteDTO dto);
	
	List<FreteDTO> toDTOList(List<FreteModel> modelList);
}
