package com.musicstore.musicstoreapi.dto.response.authDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AccessTokenResponse {
    private String accessToken;
}
