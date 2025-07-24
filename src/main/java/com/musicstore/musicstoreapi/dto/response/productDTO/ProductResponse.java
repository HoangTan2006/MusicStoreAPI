package com.musicstore.musicstoreapi.dto.response.productDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class ProductResponse {
    private Integer id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
}
