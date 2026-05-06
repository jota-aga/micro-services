package com.jh.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jh.product_service.dto.ProductRequest;
import com.jh.product_service.dto.ProductResponse;
import com.jh.product_service.entity.Product;
import com.jh.product_service.mapper.ProductMapper;
import com.jh.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	@Transactional	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
								 .name(productRequest.name())
								 .descrption(productRequest.description())
								 .price(productRequest.price())
								 .build();
		
		productRepository.save(product);
		log.info("product was saved");
	}
	
	public List<ProductResponse> getAllProducts(){
		List<Product> products = productRepository.findAll();
		
		return products.stream()
					   .map(p -> ProductMapper.INSTANCE.productToProductResponse(p))
					   .toList();
	}
}
