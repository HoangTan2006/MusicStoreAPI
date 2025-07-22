package com.musicstore.musicstoreapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Builder
public class ApiResponse<T> {
    private Instant timestamp;
    private int statusCode;
    private boolean success;
    private String message;
    private T data;
    private String path;
}
