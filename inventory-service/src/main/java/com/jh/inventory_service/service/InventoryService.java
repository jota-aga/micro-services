package com.jh.inventory_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jh.inventory_service.dto.InventoryResponse;
import com.jh.inventory_service.entity.Inventory;
import com.jh.inventory_service.mapper.InventoryMapper;
import com.jh.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
	public List<InventoryResponse> isAllInStock(List<String> skuCodes) {
		List<Inventory> inventorys = inventoryRepository.findAllBySkuCodeIn(skuCodes);
		
		List<InventoryResponse> response = inventorys.stream()
				.map(inventory -> InventoryMapper.toInvetoryResponse(inventory))
				.toList();
		
		return response;
	}
}
