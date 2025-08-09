package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.entity.Order;
import com.musicstore.musicstoreapi.entity.OrderDetail;
import com.musicstore.musicstoreapi.entity.Product;

import java.util.List;

public interface OrderDetailsService {
    List<OrderDetail> createOrderDetails(Order order, Product product);
}
