	package com.jh.inventory_service.integration;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
	import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
	import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
	import org.testcontainers.junit.jupiter.Container;
	import org.testcontainers.junit.jupiter.Testcontainers;
	import org.testcontainers.mysql.MySQLContainer;
	
	import com.jh.inventory_service.dto.InventoryResponse;
	import com.jh.inventory_service.entity.Inventory;
import com.jh.inventory_service.mapper.InventoryMapper;
import com.jh.inventory_service.repository.InventoryRepository;
	
	import tools.jackson.core.JacksonException;
	import tools.jackson.databind.ObjectMapper;
	
	@SpringBootTest
	@Testcontainers
	@AutoConfigureMockMvc
	public class InventoryControllerIntegrationTest {
		
		@Container
		@ServiceConnection
		static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7.34");
		
		@Autowired
		private MockMvc mockMvc;
		
		@Autowired
		private ObjectMapper objectMapper;
		
		@Autowired
		private InventoryRepository inventoryRepository;
		
		private Inventory i1;
		
		private Inventory i2;
		
		@BeforeEach
		public void setUp() {
			i1 = i1.builder()
			.quantity(50)
			.skuCode("item-1")
			.build();
			
			i2 = i2.builder()
			.quantity(40)
			.skuCode("item-2")
			.build();
			
			List<Inventory> inventories = new ArrayList<Inventory>();
			inventories.add(i1);
			inventories.add(i2);
			
			inventoryRepository.saveAll(inventories);
		}
		
		@Test
		public void shouldIndicateIfIsInStock() throws JacksonException, Exception {
			List<InventoryResponse> response = new ArrayList<>();
			response.add(InventoryMapper.toInvetoryResponse(i1));
			response.add(InventoryMapper.toInvetoryResponse(i2));
	
			mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory")
					.param("skuCodes", i1.getSkuCode(), i2.getSkuCode()))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(response)));
		}
	}
