package com.musicstore.musicstoreapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Builder
public class ErrorResponse {
    private Instant timestamp;
    private int statusCode;
    private String error;
    private String path;
}
