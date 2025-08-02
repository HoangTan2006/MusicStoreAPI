package com.musicstore.musicstoreapi.service.impl;

import com.musicstore.musicstoreapi.dto.request.authDTO.RegisterRequest;
import com.musicstore.musicstoreapi.entity.Cart;
import com.musicstore.musicstoreapi.entity.Role;
import com.musicstore.musicstoreapi.entity.User;
import com.musicstore.musicstoreapi.entity.enums.RoleType;
import com.musicstore.musicstoreapi.exception.UserRegistrationException;
import com.musicstore.musicstoreapi.repository.CartRepository;
import com.musicstore.musicstoreapi.repository.RoleRepository;
import com.musicstore.musicstoreapi.repository.UserRepository;
import com.musicstore.musicstoreapi.service.CartService;
import com.musicstore.musicstoreapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional // Ensure that User and Cart are created together within the same transaction
    public void createUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserRegistrationException("Username already exists");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserRegistrationException("Email already exists");
        }

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

        userRepository.save(user);
        cartService.createCart(user);
    }
}
