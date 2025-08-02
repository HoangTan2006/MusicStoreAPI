package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.dto.request.authDTO.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(RegisterRequest registerRequest);
}
