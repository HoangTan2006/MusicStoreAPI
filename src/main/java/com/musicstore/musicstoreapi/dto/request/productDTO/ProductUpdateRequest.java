package com.musicstore.musicstoreapi.dto.request.productDTO;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductUpdateRequest {
    private BigDecimal price;
    private Integer stockQuantity;
}
