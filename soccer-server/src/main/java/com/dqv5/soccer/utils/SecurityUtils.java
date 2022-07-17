package com.dqv5.soccer.utils;

import com.dqv5.soccer.security.AuthUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;

/**
 * @author duq
 */
public final class SecurityUtils {

    /**
     * 获取当前用户
     */
    public static AuthUser getCurrentUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUser) {
            return (AuthUser) principal;
        }
        return new AuthUser("Anonymous", "NoPassword", new HashSet<>());
    }

    public static String getCurrentUserName() {
        return getCurrentUserDetail().getUsername();
    }

    public static String getCurrentUserNickName() {
        return getCurrentUserDetail().getNickName();
    }

    public static String getCurrentUserId() {
        return getCurrentUserDetail().getUserId();
    }

    public static void refreshUserInfo() {
        String username = getCurrentUserName();
        UserDetailsService userDetailsService = SpringUtils.getBean(UserDetailsService.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails == null ? null : userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
