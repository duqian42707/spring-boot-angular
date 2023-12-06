package com.dqv5.soccer.security;

import com.dqv5.soccer.management.mapper.SysUserMapper;
import com.dqv5.soccer.management.table.SysRole;
import com.dqv5.soccer.management.table.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查出用户对象
        SysUser sysUser = sysUserMapper.findByAccount(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在!"));

        // todo 查出用户拥有的角色列表
        Set<SysRole> roles = new HashSet<>();
        // 构造权限集合
        Set<GrantedAuthority> auths = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleValue()))
                .collect(Collectors.toSet());
        // 构造一个UserDetails对象返回，至少需要这些参数：用户名、密码、权限集合
        return new AuthUser(sysUser.getUserId(), sysUser.getAccount(), sysUser.getPassword(), sysUser.getNickName(), sysUser.getAvatarUrl(), sysUser.getGender(), auths);
    }
}
