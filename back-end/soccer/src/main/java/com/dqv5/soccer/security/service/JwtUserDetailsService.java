package com.dqv5.soccer.security.service;

import com.dqv5.soccer.entity.BasicUser;
import com.dqv5.soccer.repository.BasicUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    private BasicUserRepository basicUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("Username should not be null!");
        }
        BasicUser basicUser = basicUserRepository.findByAccount(username);
        if (basicUser != null) {
            return basicUser;
        } else {
            throw new UsernameNotFoundException(String.format("Username [%s] not found!", username));
        }
    }
}
