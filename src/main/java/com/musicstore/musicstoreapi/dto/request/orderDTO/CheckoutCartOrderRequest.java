package com.musicstore.musicstoreapi.dto.request.orderDTO;

import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

@Getter
public abstract class CheckoutCartOrderRequest {
    @NumberFormat
    private Long addressId;

    private String paymentMethod;
}
