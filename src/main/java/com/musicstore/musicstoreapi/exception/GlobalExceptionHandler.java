package com.musicstore.musicstoreapi.exception;

import com.musicstore.musicstoreapi.dto.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(
            HttpServletRequest request,
            MethodArgumentNotValidException exception) {

        return ErrorResponse.builder()
                .timestamp(new Date())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityNotFoundException(
            HttpServletRequest request,
            EntityNotFoundException exception) {

        return ErrorResponse.builder()
                .timestamp(new Date())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .error(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
