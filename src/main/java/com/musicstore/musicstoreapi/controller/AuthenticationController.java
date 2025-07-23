package com.musicstore.musicstoreapi.controller;

import com.musicstore.musicstoreapi.dto.request.authDTO.LoginRequest;
import com.musicstore.musicstoreapi.dto.request.authDTO.RegisterRequest;
import com.musicstore.musicstoreapi.dto.response.ApiResponse;
import com.musicstore.musicstoreapi.dto.response.authDTO.LoginResponse;
import com.musicstore.musicstoreapi.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<LoginResponse> login(
            @RequestBody @Valid LoginRequest loginRequest,
            HttpServletRequest httpServletRequest) {

        LoginResponse responseData = authenticationService.authenticateUser(loginRequest);

        return ApiResponse.<LoginResponse>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("Login success")
                .data(responseData)
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> register(
            @RequestBody @Valid RegisterRequest registerRequest,
            HttpServletRequest httpServletRequest) {

        authenticationService.registerUser(registerRequest);

        return ApiResponse.<Void>builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .message("Login success")
                .data(null)
                .path(httpServletRequest.getRequestURI())
                .build();
    }
}
