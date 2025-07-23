package com.musicstore.musicstoreapi.dto.response.productDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ListProductResponse {
    private List<ProductResponse> products;
    private Integer totalPage;
}
