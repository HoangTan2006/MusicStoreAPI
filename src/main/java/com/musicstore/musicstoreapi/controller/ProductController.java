package com.musicstore.musicstoreapi.controller;

import com.musicstore.musicstoreapi.dto.request.productDTO.ProductRequest;
import com.musicstore.musicstoreapi.dto.request.productDTO.ProductUpdateRequest;
import com.musicstore.musicstoreapi.dto.response.ApiResponse;
import com.musicstore.musicstoreapi.dto.response.productDTO.ListProductResponse;
import com.musicstore.musicstoreapi.dto.response.productDTO.ProductDetailResponse;
import com.musicstore.musicstoreapi.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ListProductResponse> getProducts(
            HttpServletRequest httpServletRequest,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sort", required = false) String sort
            ) {

        ListProductResponse listProductResponse = productService.getProducts(page, size, sort);

        return ApiResponse.<ListProductResponse>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("Get products success")
                .data(listProductResponse)
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductDetailResponse> getProductDetail(
            HttpServletRequest httpServletRequest,
            @PathVariable(name = "id") Integer id) {

        ProductDetailResponse productDetailResponse = productService.getProduct(id);

        return ApiResponse.<ProductDetailResponse>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("Get product id: " + id + " success")
                .data(productDetailResponse)
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> createProduct(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid ProductRequest productRequest) {

        productService.createProduct(productRequest);

        return ApiResponse.<Void>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("Product has been created")
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> updateProduct(
            HttpServletRequest httpServletRequest,
            @PathVariable(name = "id") Integer id,
            @RequestBody @Valid ProductUpdateRequest productUpdateRequest) {

        productService.updateProduct(id, productUpdateRequest);

        return ApiResponse.<Void>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .success(true)
                .message("Product has been updated")
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteProduct(
            HttpServletRequest httpServletRequest,
            @PathVariable(name = "id") Integer id) {

        productService.softDeleteProduct(id);

        return ApiResponse.<Void>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .success(true)
                .message("Product has been deleted")
                .path(httpServletRequest.getRequestURI())
                .build();
    }
}
