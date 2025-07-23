package com.musicstore.musicstoreapi.dto.request.productDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

@Getter
public class ProductRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @URL(message = "Invalid url format")
    private String imageUrl;

    private String description;

    @NumberFormat
    private Long price;

    @NumberFormat
    private Integer stockQuantity;

    @NumberFormat
    private Integer soldQuantity;

    @NumberFormat
    private Integer categoryId;
}
