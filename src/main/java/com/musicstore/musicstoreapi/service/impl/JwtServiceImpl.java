package com.musicstore.musicstoreapi.service.impl;

import com.musicstore.musicstoreapi.configuration.JwtConfig;
import com.musicstore.musicstoreapi.entity.Role;
import com.musicstore.musicstoreapi.entity.User;
import com.musicstore.musicstoreapi.entity.enums.TokenType;
import com.musicstore.musicstoreapi.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtConfig jwtConfig;

    @Override
    public String generateToken(User user, TokenType tokenType) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
//                .claim("roles", buildClaimRoles(user.getRoles()))
                .issuedAt(new Date())
                .expiration(jwtConfig.getExpiration(tokenType))
                .issuer(jwtConfig.getISSUER())
                .signWith(jwtConfig.getSecretKey(tokenType))
                .compact();
    }

    @Override
    public Claims parserToken(String token, TokenType tokenType) {
        return Jwts.parser()
                .requireIssuer(jwtConfig.getISSUER())
                .verifyWith(jwtConfig.getSecretKey(tokenType))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
//    private List<String> buildClaimRoles(Set<Role> roles) {
//        return roles
//                .stream()
//                .map(Role::getName)
//                .toList();
//    }
}
