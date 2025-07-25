package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.entity.Cart;
import com.musicstore.musicstoreapi.entity.CartItem;
import com.musicstore.musicstoreapi.entity.Product;
import com.musicstore.musicstoreapi.entity.User;
import com.musicstore.musicstoreapi.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBeans;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    void testGetCart() {
        User userMock = User.builder()
                .name("a")
                .username("a")
                .password("a")
                .build();

        userMock.setId(1L);

        Product productMock = Product.builder()
                .name("guitar")
                .price(BigDecimal.valueOf(1000000))
                .build();

        CartItem cartItemMock = CartItem.builder()
                .quantity(2)
                .build();



        Cart cartMock = Cart.builder()
                .user(userMock)
                .cartItems()
                .build();
    }
}
