package com.jh.order_service.integration;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.List;

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
import org.testcontainers.mysql.MySQLContainer;

import com.jh.order_service.controller.dto.OrderLineItemsRequest;
import com.jh.order_service.controller.dto.OrderRequest;
import com.jh.order_service.entity.Order;
import com.jh.order_service.repository.OrderRepository;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {
	
	@Container
	@ServiceConnection
	static MySQLContainer mongoDBContainer = new MySQLContainer("mysql:5.7.34");
	
	private static String url = "/api/order";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	public void shouldCreate() throws JacksonException, Exception {
		OrderLineItemsRequest request = new OrderLineItemsRequest("xiamoi-14", 100, new BigDecimal(5000));
		OrderRequest orderRequest = new OrderRequest(List.of(request));
		
		mockMvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(orderRequest)))
		.andExpect(MockMvcResultMatchers.status().isCreated());
		
		List<Order> orders = orderRepository.findAll();
		
		assertFalse(orders.isEmpty());
	}
}
