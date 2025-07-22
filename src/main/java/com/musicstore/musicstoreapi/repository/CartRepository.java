package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}