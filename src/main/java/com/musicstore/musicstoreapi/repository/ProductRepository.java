package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}