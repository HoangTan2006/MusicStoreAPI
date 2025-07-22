package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}