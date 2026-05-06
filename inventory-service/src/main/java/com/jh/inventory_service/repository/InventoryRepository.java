package com.jh.inventory_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jh.inventory_service.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	List<Inventory> findAllBySkuCodeIn(List<String> skuCodes);
}
