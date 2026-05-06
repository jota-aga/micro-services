package com.jh.product_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jh.product_service.dto.ProductResponse;
import com.jh.product_service.entity.Product;

@Mapper
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	ProductResponse productToProductResponse(Product product);
}
