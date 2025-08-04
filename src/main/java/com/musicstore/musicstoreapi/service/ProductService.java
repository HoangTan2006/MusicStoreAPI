package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.dto.request.productDTO.ProductRequest;
import com.musicstore.musicstoreapi.dto.request.productDTO.ProductUpdateRequest;
import com.musicstore.musicstoreapi.dto.response.productDTO.ListProductResponse;
import com.musicstore.musicstoreapi.dto.response.productDTO.ProductDetailResponse;

public interface ProductService {
    void createProduct(ProductRequest productRequest);
    ProductDetailResponse getProduct(Integer id);
    ListProductResponse getProducts(Integer page, Integer size, String sort);
    void updateProduct(Integer id, ProductUpdateRequest productUpdate);
    void softDeleteProduct(Integer id);
}
