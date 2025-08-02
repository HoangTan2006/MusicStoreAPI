package com.musicstore.musicstoreapi.controller;

import com.musicstore.musicstoreapi.dto.request.cartDTO.CartItemRequest;
import com.musicstore.musicstoreapi.dto.request.cartDTO.UpdateQuantityCartItemRequest;
import com.musicstore.musicstoreapi.dto.response.ApiResponse;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse;
import com.musicstore.musicstoreapi.dto.response.cartDTO.CartResponse;
import com.musicstore.musicstoreapi.service.CartService;
import com.musicstore.musicstoreapi.utils.SecurityUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CartResponse> getCard(
            HttpServletRequest httpServletRequest) {

        Long currentUserId = SecurityUtils.getCurrentUserId();

        CartResponse cartResponse = cartService.getCart(currentUserId);

        return ApiResponse.<CartResponse>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("Get card of userId: " )
                .data(cartResponse)
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @PostMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CartItemResponse> addCardItem(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid CartItemRequest cartItemRequest) {

        Long currentUserId = SecurityUtils.getCurrentUserId();

        CartItemResponse cartItemResponse = cartService.addCartItem(currentUserId, cartItemRequest);

        return ApiResponse.<CartItemResponse>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("Add card item Success")
                .data(cartItemResponse)
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @PatchMapping("/me/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CartItemResponse> updateQuantityForItem(
            HttpServletRequest httpServletRequest,
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid UpdateQuantityCartItemRequest updateQuantityCartItemRequest) {

        Long currentUserId = SecurityUtils.getCurrentUserId();

        CartItemResponse cartItemResponse = cartService.updateQuantityForItem(currentUserId, id, updateQuantityCartItemRequest);

        return ApiResponse.<CartItemResponse>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("update quantity for card item Success")
                .data(cartItemResponse)
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @DeleteMapping("/me/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteCartItem(
            HttpServletRequest httpServletRequest,
            @PathVariable(name = "id") Long id) {

        Long currentUserId = SecurityUtils.getCurrentUserId();

        cartService.deleteItem(currentUserId, id);

        return ApiResponse.<Void>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .success(true)
                .message("Delete card item Success")
                .path(httpServletRequest.getRequestURI())
                .build();
    }
}
