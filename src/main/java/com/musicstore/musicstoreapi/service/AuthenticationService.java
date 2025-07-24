package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.dto.request.authDTO.LoginRequest;
import com.musicstore.musicstoreapi.dto.request.authDTO.RefreshTokenRequest;
import com.musicstore.musicstoreapi.dto.request.authDTO.RegisterRequest;
import com.musicstore.musicstoreapi.dto.response.authDTO.AccessTokenResponse;
import com.musicstore.musicstoreapi.dto.response.authDTO.LoginResponse;

public interface AuthenticationService {
    LoginResponse authenticateUser(LoginRequest loginRequest);
    void registerUser(RegisterRequest registerRequest);
    AccessTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
