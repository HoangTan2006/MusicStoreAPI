package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}