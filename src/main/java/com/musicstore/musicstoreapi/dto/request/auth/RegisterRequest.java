package com.musicstore.musicstoreapi.dto.request.auth;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String name;
    private String email;
    private String username;
    private String password;
}
