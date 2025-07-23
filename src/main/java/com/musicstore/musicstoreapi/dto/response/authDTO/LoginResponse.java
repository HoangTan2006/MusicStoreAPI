package com.musicstore.musicstoreapi.dto.response.authDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginResponse {
    private String name;
    private String accessToken;
    private String refreshToken;
}
