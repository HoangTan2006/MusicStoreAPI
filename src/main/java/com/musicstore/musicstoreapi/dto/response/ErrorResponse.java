package com.musicstore.musicstoreapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Setter
@Getter
@Builder
public class ErrorResponse {
    private Date timestamp;
    private int statusCode;
    private String error;
    private String path;
}
