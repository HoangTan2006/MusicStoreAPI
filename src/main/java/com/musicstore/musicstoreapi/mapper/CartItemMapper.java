package com.musicstore.musicstoreapi.mapper;

import com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse;
import com.musicstore.musicstoreapi.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    public CartItemResponse toCartItemDTO(CartItem cartItem) {
        return CartItemResponse.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .productName(cartItem.getProduct().getName())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }
}
