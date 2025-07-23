package com.musicstore.musicstoreapi.dto.response.productDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductDetailResponse {
    private Integer id;
    private String name;
    private String imageUrl;
    private String description;
    private Long price;
    private Integer stockQuantity;
}
