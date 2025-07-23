package com.musicstore.musicstoreapi.exception;

import com.musicstore.musicstoreapi.dto.response.ErrorResponse;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(
            HttpServletRequest request,
            MethodArgumentNotValidException exception) {

        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
