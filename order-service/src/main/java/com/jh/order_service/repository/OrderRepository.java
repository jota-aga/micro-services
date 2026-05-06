package com.jh.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jh.order_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
