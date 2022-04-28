package com.azship.freight.utils;

import java.util.List;

import com.azship.freight.dtos.PageDTO;

public class PageableUtils {
	
	public static <T> PageDTO<T> generatePageDTO(List<T> content, Long totalElements,
			int totalPages, int pageNumber, int requestSize) {
		
		PageDTO<T> pageDTO = new PageDTO<T>();
		
		pageDTO.setContent(content);
		pageDTO.setPageNumber(pageNumber == 0 ? 1 : pageNumber + 1);
		pageDTO.setRequestSize(requestSize);
		pageDTO.setTotalElements(totalElements);
		pageDTO.setTotalPages(totalPages);
		
		return pageDTO;
	}
}
