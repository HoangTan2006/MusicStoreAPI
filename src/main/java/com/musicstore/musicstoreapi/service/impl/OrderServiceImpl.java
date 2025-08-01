package com.musicstore.musicstoreapi.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.musicstore.musicstoreapi.dto.request.orderDTO.BuyNowOrderRequest;
import com.musicstore.musicstoreapi.dto.request.orderDTO.CheckoutCartOrderRequest;
import com.musicstore.musicstoreapi.dto.response.orderDTO.OrderResponse;
import com.musicstore.musicstoreapi.entity.*;
import com.musicstore.musicstoreapi.entity.enums.OrderStatus;
import com.musicstore.musicstoreapi.mapper.OrderMapper;
import com.musicstore.musicstoreapi.repository.AddressRepository;
import com.musicstore.musicstoreapi.repository.OrderRepository;
import com.musicstore.musicstoreapi.repository.ProductRepository;
import com.musicstore.musicstoreapi.repository.UserRepository;
import com.musicstore.musicstoreapi.service.OrderService;
import com.musicstore.musicstoreapi.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final AddressRepository addressRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @SneakyThrows
    @Override
    public OrderResponse createOrderFromBuyNow(Long userId, BuyNowOrderRequest buyNowOrderRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Product product = productRepository.findByIdAndIsDeletedFalse(buyNowOrderRequest.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        //Convert address to json
        Address address = user.getAddresses()
                .stream()
                .filter(a -> a.getId().equals(buyNowOrderRequest.getAddressId()))
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("Access denied"));

        String addressJson = address.toJson();

        BigDecimal quantityOrder = BigDecimal.valueOf(buyNowOrderRequest.getQuantity());
        BigDecimal totalAmount = product.getPrice().multiply(quantityOrder);

        Order order = Order.builder()
                .user(user)
                .totalAmount(totalAmount)
                .address(addressJson)
                .status(OrderStatus.PENDING.name())
                .build();

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .quantity(buyNowOrderRequest.getQuantity())
                .price(totalAmount)
                .build();

        order.setOrderDetail(List.of(orderDetail));
        return orderMapper.toDTO(
                orderRepository.save(order));
    }

    @Override
    public OrderResponse createOrderFromCheckoutCart(Long userId, CheckoutCartOrderRequest checkoutCartOrderRequest) {
        return null;
    }
}
