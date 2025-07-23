package com.musicstore.musicstoreapi.dto.response.productDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductResponse {
    private Integer id;
    private String name;
    private String imageUrl;
    private Long price;
}
