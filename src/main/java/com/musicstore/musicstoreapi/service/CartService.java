package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.dto.request.cartDTO.CartItemRequest;
import com.musicstore.musicstoreapi.dto.request.cartDTO.UpdateQuantityCartItemRequest;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartResponse;
import com.musicstore.musicstoreapi.entity.User;

public interface CartService {
    void createCart(User user);
    CartResponse getCart(Long userId);
    CartItemResponse addCartItem(Long userId, CartItemRequest cartItemRequest);
    CartItemResponse updateQuantityForItem(Long userId, UpdateQuantityCartItemRequest updateCartItem);
    void deleteItem(Long userId, Long cartItemId);
}
