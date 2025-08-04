package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.entity.CustomUserDetails;
import com.musicstore.musicstoreapi.entity.enums.TokenType;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(CustomUserDetails userDetails, TokenType tokenType);
    Claims parserToken(String token, TokenType tokenType);
}
