package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.entity.Product;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByIsDeletedFalse(Pageable pageable);
    Optional<Product> findByIdAndIsDeletedFalse(Integer id);
}