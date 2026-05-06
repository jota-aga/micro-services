package com.jh.inventory_service.mapper;

import com.jh.inventory_service.dto.InventoryResponse;
import com.jh.inventory_service.entity.Inventory;

public class InventoryMapper {
	
	public static InventoryResponse toInvetoryResponse(Inventory inventory) {
		return new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity() > 0);
	}
}
