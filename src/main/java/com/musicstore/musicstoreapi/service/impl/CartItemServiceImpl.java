package com.musicstore.musicstoreapi.service.impl;

import com.musicstore.musicstoreapi.dto.request.cartDTO.UpdateQuantityCartItemRequest;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse;
import com.musicstore.musicstoreapi.entity.CartItem;
import com.musicstore.musicstoreapi.mapper.CartItemMapper;
import com.musicstore.musicstoreapi.repository.CartItemRepository;
import com.musicstore.musicstoreapi.service.CartItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemResponse updateQuantity(Long userId, UpdateQuantityCartItemRequest updateCartItem) {
        CartItem cartItem = cartItemRepository.findByIdAndCart_User_Id(updateCartItem.getId(), userId)
                        .orElseThrow(() -> new EntityNotFoundException("cartItem not found"));

        cartItem.setQuantity(updateCartItem.getQuantity());

        return cartItemMapper.toCartItemDTO(
                cartItemRepository.save(cartItem));
    }

    @Override
    public void deleteCartItem(Long userId, Long cartItemId) {
        if (cartItemRepository.existsByIdAndCart_User_Id(cartItemId, userId)) {
            cartItemRepository.deleteById(cartItemId);
        } else throw new AccessDeniedException("Access denied");
    }
}
