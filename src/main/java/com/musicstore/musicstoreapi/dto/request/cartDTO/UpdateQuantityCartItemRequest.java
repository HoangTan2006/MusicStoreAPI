package com.musicstore.musicstoreapi.dto.request.cartDTO;

import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

@Getter
public class UpdateQuantityCartItemRequest {
    @NumberFormat
    private Integer quantity;
}
