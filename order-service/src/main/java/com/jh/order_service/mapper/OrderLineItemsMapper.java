package com.jh.order_service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jh.order_service.controller.dto.OrderLineItemsRequest;
import com.jh.order_service.entity.OrderLineItems;

@Mapper
public interface OrderLineItemsMapper {
	
	OrderLineItemsMapper INSTANCE = Mappers.getMapper(OrderLineItemsMapper.class);
	
	OrderLineItems orderLineItemsRequestToOrderLineItems(OrderLineItemsRequest orderLineItemsRequest);
	List<OrderLineItems> listDtoToListEntity(List<OrderLineItemsRequest> list);
}
