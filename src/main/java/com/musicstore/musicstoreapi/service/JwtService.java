package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.entity.User;
import com.musicstore.musicstoreapi.entity.enums.TokenType;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(User user, TokenType tokenType);
    Claims verifyToken(String token, TokenType tokenType);
}
