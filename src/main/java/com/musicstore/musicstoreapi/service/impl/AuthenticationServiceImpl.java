package com.musicstore.musicstoreapi.service.impl;

import com.musicstore.musicstoreapi.dto.request.authDTO.LoginRequest;
import com.musicstore.musicstoreapi.dto.request.authDTO.RefreshTokenRequest;
import com.musicstore.musicstoreapi.dto.request.authDTO.RegisterRequest;
import com.musicstore.musicstoreapi.dto.response.authDTO.AccessTokenResponse;
import com.musicstore.musicstoreapi.dto.response.authDTO.LoginResponse;
import com.musicstore.musicstoreapi.entity.Cart;
import com.musicstore.musicstoreapi.entity.Role;
import com.musicstore.musicstoreapi.entity.User;
import com.musicstore.musicstoreapi.entity.enums.RoleType;
import com.musicstore.musicstoreapi.entity.enums.TokenType;
import com.musicstore.musicstoreapi.exception.UserRegistrationException;
import com.musicstore.musicstoreapi.repository.CartRepository;
import com.musicstore.musicstoreapi.repository.RoleRepository;
import com.musicstore.musicstoreapi.repository.UserRepository;
import com.musicstore.musicstoreapi.service.AuthenticationService;
import com.musicstore.musicstoreapi.service.CartService;
import com.musicstore.musicstoreapi.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CartRepository cartRepository;

    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(user, TokenType.ACCESS);
        String refreshToken = jwtService.generateToken(user, TokenType.REFRESH);

        return LoginResponse.builder()
                .name(user.getName())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional
    public void registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) throw new UserRegistrationException("Username already exists");
        if (userRepository.existsByEmail(registerRequest.getEmail())) throw new UserRegistrationException("Email already exists");

        Role role = roleRepository.findByName(RoleType.ROLE_USER.name())
                .orElseThrow(() -> new UserRegistrationException("Role not found"));

        User user = User.builder()
                        .name(registerRequest.getName())
                        .email(registerRequest.getEmail())
                        .username(registerRequest.getUsername())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .roles(Set.of(role))
                        .isLock(false)
                        .build();

        user = userRepository.save(user);

        //Only create a default cart when the user has just registered an account
        Cart cart = new Cart(user, null);
        cartRepository.save(cart);
//        user.setCart(cart);
    }

    @Override
    public AccessTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Claims parserToken = jwtService.parserToken(refreshTokenRequest.getRefreshToken(), TokenType.REFRESH);
        String username = parserToken.getSubject();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        String accessToken = jwtService.generateToken(user, TokenType.ACCESS);
        return new AccessTokenResponse(accessToken);
    }
}
