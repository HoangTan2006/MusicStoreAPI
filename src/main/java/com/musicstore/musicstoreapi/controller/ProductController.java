package com.musicstore.musicstoreapi.controller;

import com.musicstore.musicstoreapi.dto.response.ApiResponse;
import com.musicstore.musicstoreapi.dto.response.productDTO.ListProductResponse;
import com.musicstore.musicstoreapi.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
                .message("success")
                .data(listProductResponse)
                .path(httpServletRequest.getRequestURI())
                .build();
    }
}
