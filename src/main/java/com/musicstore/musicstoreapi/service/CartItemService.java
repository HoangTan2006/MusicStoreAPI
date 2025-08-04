package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.dto.request.cartDTO.UpdateQuantityCartItemRequest;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse;

public interface CartItemService {
    CartItemResponse updateQuantity(Long userId, UpdateQuantityCartItemRequest updateCartItem);
    void deleteCartItem(Long userId, Long cartItemId);
    void deleteCartItemByProductId(Integer productId);
}
