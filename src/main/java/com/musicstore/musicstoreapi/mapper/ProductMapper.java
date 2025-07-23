package com.musicstore.musicstoreapi.mapper;

import com.musicstore.musicstoreapi.dto.response.productDTO.ProductDetailResponse;
import com.musicstore.musicstoreapi.dto.response.productDTO.ProductResponse;
import com.musicstore.musicstoreapi.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toProductDTO(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .build();
    }

    public ProductDetailResponse toProductDetailDTO(Product product) {
        return ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }
}
