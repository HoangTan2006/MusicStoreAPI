package com.musicstore.musicstoreapi.filter;

import com.musicstore.musicstoreapi.entity.enums.TokenType;
import com.musicstore.musicstoreapi.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String accessToken = authorization.substring(7);
            try {
                Claims parserToken = jwtService.parserToken(accessToken, TokenType.ACCESS);
                List<String> roles = parserToken.get("roles", List.class);

                List<SimpleGrantedAuthority> authorities = roles
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        parserToken.get("userId", Long.class),
                        null,
                        authorities);

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            } catch (JwtException e) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("{\"message\": \"Jwt token invalid\"}");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
