package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}