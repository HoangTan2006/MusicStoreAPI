package com.musicstore.musicstoreapi.configuration;

import com.musicstore.musicstoreapi.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final String[] WHITE_LIST = {
            "/api/v1/auth/login",
            "/api/v1/auth/refresh-token",
            "/api/v1/auth/register",
            "/api/v1/products/**",
            "/api/v1/products"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .exceptionHandling(exceptionHandleConfig -> exceptionHandleConfig
                        .authenticationEntryPoint(restAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(config -> config
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/products/").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/products/").authenticated()
                        .anyRequest().authenticated())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
         return configuration.getAuthenticationManager();
    }
}
