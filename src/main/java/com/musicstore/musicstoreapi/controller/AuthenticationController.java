package com.musicstore.musicstoreapi.controller;

import com.musicstore.musicstoreapi.dto.request.auth.LoginRequest;
import com.musicstore.musicstoreapi.dto.request.auth.RegisterRequest;
import com.musicstore.musicstoreapi.dto.response.ApiResponse;
import com.musicstore.musicstoreapi.dto.response.LoginResponse;
import com.musicstore.musicstoreapi.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest loginRequest,
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
    public ApiResponse<Void> register(
            @RequestBody RegisterRequest registerRequest,
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
