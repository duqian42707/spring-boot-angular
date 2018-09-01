package com.dqv5.soccer.security.service;

import com.dqv5.soccer.dao.BasicUserMapper;
import com.dqv5.soccer.entity.BasicUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    private BasicUserMapper basicUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("Username should not be null!");
        }
        BasicUser basicUser = basicUserMapper.findByAccount(username);
        if (basicUser != null) {
            return basicUser;
        } else {
            throw new UsernameNotFoundException(String.format("Username [%s] not found!", username));
        }
    }
}
