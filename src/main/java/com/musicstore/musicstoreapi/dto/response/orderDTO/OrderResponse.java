package com.musicstore.musicstoreapi.dto.response.orderDTO;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.musicstore.musicstoreapi.entity.OrderDetail;
import com.musicstore.musicstoreapi.entity.Payment;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    @JsonRawValue
    private String address;
    private String status;
}
