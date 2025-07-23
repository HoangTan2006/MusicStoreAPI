package com.musicstore.musicstoreapi.dto.request.productDTO;

import lombok.Getter;

@Getter
public class ProductUpdateRequest {
    private Long price;
    private Integer stockQuantity;
}
