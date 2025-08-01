package com.musicstore.musicstoreapi.mapper;

import com.musicstore.musicstoreapi.dto.response.orderDTO.OrderResponse;
import com.musicstore.musicstoreapi.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toDTO(Order order);
}
