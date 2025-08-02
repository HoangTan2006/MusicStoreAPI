package com.musicstore.musicstoreapi.service.impl;

import com.musicstore.musicstoreapi.dto.request.cartDTO.CartItemRequest;
import com.musicstore.musicstoreapi.dto.request.cartDTO.UpdateQuantityCartItemRequest;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartResponse;
import com.musicstore.musicstoreapi.entity.Cart;
import com.musicstore.musicstoreapi.entity.CartItem;
import com.musicstore.musicstoreapi.entity.Product;
import com.musicstore.musicstoreapi.entity.User;
import com.musicstore.musicstoreapi.mapper.CartItemMapper;
import com.musicstore.musicstoreapi.repository.CartItemRepository;
import com.musicstore.musicstoreapi.repository.CartRepository;
import com.musicstore.musicstoreapi.repository.ProductRepository;
import com.musicstore.musicstoreapi.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void createCart(User user) {
        Cart cart = Cart.builder()
                .user(user)
                .build();

        cartRepository.save(cart);
    }

    @Override
    public CartResponse getCart(Long userId) {

        List<CartItemResponse> cartItemResponses = cartItemRepository.findAllCartItemWithProductPriceByUserId(userId);

        BigDecimal totalAmount = calculateTotalAmount(cartItemResponses);

        return CartResponse.builder()
                .cart(cartItemResponses)
                .totalAmount(totalAmount)
                .build();
    }

    @Override
    public CartItemResponse addCartItem(Long userId, CartItemRequest cartItemRequest) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        Product product = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity()));

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(cartItemRequest.getQuantity())
//                .price(price)
                .build();

        return cartItemMapper.toCartItemDTO(
                cartItemRepository.save(cartItem));
    }

    @Override
    public CartItemResponse updateQuantityForItem(Long userId, Long cartItemId, UpdateQuantityCartItemRequest quantityRequest) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> Objects.equals(item.getId(), cartItemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found"));

        BigDecimal price = cartItem.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(quantityRequest.getQuantity()));

        cartItem.setQuantity(quantityRequest.getQuantity());
//        cartItem.setPrice(price);

        return cartItemMapper.toCartItemDTO(
                cartItemRepository.save(cartItem));
    }

    @Override
    public void deleteItem(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> Objects.equals(item.getId(), cartItemId))
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("Access denied"));

        cart.getCartItems().remove(cartItem);

        cartItemRepository.delete(cartItem);
    }

    private BigDecimal calculateTotalAmount(List<CartItemResponse> cartItemResponses) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        if (cartItemResponses.isEmpty()) return BigDecimal.ZERO;

        for (CartItemResponse cartItem : cartItemResponses) {
            totalAmount = totalAmount.add(
                    BigDecimal.valueOf(cartItem.getQuantity()).multiply(cartItem.getPrice())
            );
        }

        return totalAmount;
    }
}
