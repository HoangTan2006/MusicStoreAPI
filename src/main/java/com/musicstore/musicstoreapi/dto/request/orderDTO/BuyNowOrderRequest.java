package com.musicstore.musicstoreapi.dto.request.orderDTO;

import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

@Getter
public class BuyNowOrderRequest {
    @NumberFormat
    private Integer productId;

    @NumberFormat
    private Integer quantity;

    @NumberFormat
    private Long addressId;

    private String paymentMethod;
}
