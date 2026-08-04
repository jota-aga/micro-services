package com.jh.order_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jh.order_service.controller.dto.OrderRequest;
import com.jh.order_service.entity.Order;
import com.jh.order_service.entity.OrderLineItems;
import com.jh.order_service.mapper.OrderLineItemsMapper;
import com.jh.order_service.repository.OrderRepository;
import com.jh.order_service.service.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> items = OrderLineItemsMapper.INSTANCE.listDtoToListEntity(orderRequest.orderLineItems());
		
		List<String> skuCodes = items.stream()
				.map(item -> item.getSkuCode())
				.toList();
		
		InventoryResponse[] response = webClientBuilder.build().get()
			.uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
			.retrieve()
			.bodyToMono(InventoryResponse[].class)
			.block();
		
		Boolean isAllInStock = List.of(response).stream()
			.allMatch(inventory -> inventory.isInStock());
		
		if(isAllInStock) {
			order.setOrderLineItems(items);
			
			orderRepository.save(order);
		}
		else {
			throw new RuntimeException("Algum Produto não está disponível");
		}
	}
}
