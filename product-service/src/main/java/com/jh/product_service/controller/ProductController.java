package com.jh.product_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jh.product_service.dto.ProductRequest;
import com.jh.product_service.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest){
		productService.createProduct(productRequest);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllProducts(){
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
	}
}
