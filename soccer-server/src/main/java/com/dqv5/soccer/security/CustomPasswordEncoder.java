package com.dqv5.soccer.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author duq
 * @date 2022/7/12
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return "<dqv5>" + rawPassword + "</dqv5>";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
