package com.musicstore.musicstoreapi.utils;

import com.musicstore.musicstoreapi.entity.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails userDetails) {
            return userDetails.getId();
        } else {
            throw new RuntimeException("UnAuthenticated");
        }
    }
}
