package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.dto.request.orderDTO.BuyNowOrderRequest;
import com.musicstore.musicstoreapi.dto.request.orderDTO.CheckoutCartOrderRequest;
import com.musicstore.musicstoreapi.dto.response.orderDTO.OrderResponse;

public interface OrderService {
    OrderResponse createOrderFromBuyNow(Long userId, BuyNowOrderRequest buyNowOrderRequest);
    OrderResponse createOrderFromCheckoutCart(Long userId, CheckoutCartOrderRequest checkoutCartOrderRequest);
}
