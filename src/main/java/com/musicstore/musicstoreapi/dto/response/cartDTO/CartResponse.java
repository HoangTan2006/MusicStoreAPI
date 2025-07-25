package com.musicstore.musicstoreapi.dto.response.cartDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
public class CartResponse {
    private List<CartItemResponse> cart;
    private BigDecimal totalAmount;
}
