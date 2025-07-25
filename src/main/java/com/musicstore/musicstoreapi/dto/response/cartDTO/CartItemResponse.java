package com.musicstore.musicstoreapi.dto.response.cartDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CartItemResponse {
    private Long id;
    private Integer productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
