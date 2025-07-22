package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}