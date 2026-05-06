package com.jh.order_service.controller.dto;

import java.math.BigDecimal;

public record OrderLineItemsRequest(String skuCode, Integer quantity, BigDecimal price) {}
