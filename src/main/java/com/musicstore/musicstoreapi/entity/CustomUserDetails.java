package com.musicstore.musicstoreapi.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    Long getId();
    String getEmail();
}
