package com.musicstore.musicstoreapi.dto.request.authDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshTokenRequest {
    @NotBlank(message = "RefreshToken must not be blank")
    private String refreshToken;
}
