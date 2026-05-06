package com.jh.order_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jh.order_service.controller.dto.OrderRequest;
import com.jh.order_service.entity.Order;
import com.jh.order_service.entity.OrderLineItems;
import com.jh.order_service.mapper.OrderLineItemsMapper;
import com.jh.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> items = OrderLineItemsMapper.INSTANCE.listDtoToListEntity(orderRequest.orderLineItems());
		
		order.setOrderLineItems(items);
		
		orderRepository.save(order);
	}
}
