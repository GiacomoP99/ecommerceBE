package com.aizoon.productproject.mapper;

import org.springframework.beans.BeanUtils;

import com.aizoon.productproject.dto.ProductRequestDTO;
import com.aizoon.productproject.dto.ProductResponseDTO;
import com.aizoon.productproject.model.Product;

public class ProductMapper {
	public static Product mapDtoToEntity(ProductRequestDTO dto) {
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	public static ProductResponseDTO mapEntityToDto(Product entity) {
		ProductResponseDTO dto = new ProductResponseDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
}
