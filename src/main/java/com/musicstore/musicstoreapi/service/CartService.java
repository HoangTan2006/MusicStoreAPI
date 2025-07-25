package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.dto.request.cartDTO.CartItemRequest;
import com.musicstore.musicstoreapi.dto.request.cartDTO.UpdateQuantityCartItemRequest;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartResponse;

public interface CartService {
    CartResponse getCart(Long userId);
    CartItemResponse addCartItem(Long userId, CartItemRequest cartItemRequest);
    CartItemResponse updateQuantityForItem(Long userId, Long cartItemId, UpdateQuantityCartItemRequest quantityRequest);
    void deleteItem(Long userId, Long cartItemId);
}
