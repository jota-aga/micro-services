package com.jh.order_service.controller.dto;

import java.util.List;

public record OrderRequest(List<OrderLineItemsRequest> orderLineItems) {

}
