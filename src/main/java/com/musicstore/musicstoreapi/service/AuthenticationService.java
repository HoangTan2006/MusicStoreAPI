package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.dto.request.auth.LoginRequest;
import com.musicstore.musicstoreapi.dto.request.auth.RegisterRequest;
import com.musicstore.musicstoreapi.dto.response.LoginResponse;

import javax.management.relation.RoleNotFoundException;

public interface AuthenticationService {
    LoginResponse authenticateUser(LoginRequest request);
    void registerUser(RegisterRequest request);
}
