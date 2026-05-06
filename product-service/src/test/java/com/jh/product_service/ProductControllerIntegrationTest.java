package com.jh.product_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import com.jh.product_service.dto.ProductRequest;
import com.jh.product_service.entity.Product;
import com.jh.product_service.repository.ProductRepository;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {
	
	@Container
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");
	
	private static String url = "/api/product";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@BeforeEach
	public void setUp() {
		productRepository.deleteAll();
	}
	
	@Test
	public void shouldCreateProduct() throws JacksonException, Exception {
		ProductRequest productRequest = new ProductRequest("name", "description", new BigDecimal(10));
		
		mockMvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productRequest)))
		.andExpect(MockMvcResultMatchers.status().isCreated());
		
		List<Product> products =productRepository.findAll();
	
		assertEquals(products.size(), 1);
	}
	
	@Test
	public void shouldReturnAllProducts() throws JacksonException, Exception {
		Product p1 = Product.builder()
				.name("name 1")
				.descrption("description 1")
				.price(new BigDecimal(10))
				.build();
		
		Product p2 = Product.builder()
				.name("name 2")
				.descrption("description 2")
				.price(new BigDecimal(10))
				.build();
		
		productRepository.saveAll(List.of(p1, p2));
		
		mockMvc.perform(MockMvcRequestBuilders.get(url))
		.andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
		
	}
}
