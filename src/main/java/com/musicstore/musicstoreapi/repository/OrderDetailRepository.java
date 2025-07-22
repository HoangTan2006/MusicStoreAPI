package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}