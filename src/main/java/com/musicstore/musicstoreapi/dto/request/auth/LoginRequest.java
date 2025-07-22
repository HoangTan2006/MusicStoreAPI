package com.musicstore.musicstoreapi.dto.request.auth;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
