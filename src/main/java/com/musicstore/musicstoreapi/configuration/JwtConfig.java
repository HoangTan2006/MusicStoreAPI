package com.musicstore.musicstoreapi.configuration;

import com.musicstore.musicstoreapi.entity.enums.TokenType;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtConfig {
    @Value("${jwt.access.token.secret.key}")
    private String ACCESS_TOKEN_SECRET_KEY;

    @Value("${jwt.refresh.token.secret.key}")
    private String REFRESH_TOKEN_SECRET_KEY;

    @Value("${jwt.access.token.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;

    @Value("${jwt.refresh.token.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    @Getter
    @Value("${jwt.issuer}")
    private String ISSUER;

    public SecretKey getSecretKey(TokenType tokenType) {
        return Keys.hmacShaKeyFor((TokenType.ACCESS.equals(tokenType)) ? ACCESS_TOKEN_SECRET_KEY.getBytes() : REFRESH_TOKEN_SECRET_KEY.getBytes());
    }

    public Date getExpiration(TokenType tokenType) {
        Date now = new Date();
        if (TokenType.ACCESS.equals(tokenType)) {
            return new Date(now.getTime() + this.ACCESS_TOKEN_EXPIRATION);
        } else {
            return new Date(now.getTime() + this.REFRESH_TOKEN_EXPIRATION);
        }
    }
}
