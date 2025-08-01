package com.musicstore.musicstoreapi.controller;

import com.musicstore.musicstoreapi.dto.request.orderDTO.BuyNowOrderRequest;
import com.musicstore.musicstoreapi.dto.response.ApiResponse;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartResponse;
import com.musicstore.musicstoreapi.dto.response.orderDTO.OrderResponse;
import com.musicstore.musicstoreapi.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OrderResponse> createOrderFromBuyNow(
            HttpServletRequest httpServletRequest,
            Authentication authentication,
            @RequestBody @Valid BuyNowOrderRequest buyNowOrderRequest) {

        OrderResponse orderResponse = orderService.createOrderFromBuyNow(
                (Long) authentication.getPrincipal(),
                buyNowOrderRequest);

        return ApiResponse.<OrderResponse>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("Get card of userId: " )
                .data(orderResponse)
                .path(httpServletRequest.getRequestURI())
                .build();
    }
}
