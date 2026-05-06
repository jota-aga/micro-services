package com.jh.product_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
		@NotBlank(message = "name must be not blank")
		String name, 
		@NotBlank(message = "description must be not blank")
		String description,
		@Positive(message = "price must be positive")
		BigDecimal price
		) 
{}
