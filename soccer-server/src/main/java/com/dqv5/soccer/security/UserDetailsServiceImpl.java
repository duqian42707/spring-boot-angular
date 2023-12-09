package com.dqv5.soccer.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.mapper.SysRoleMapper;
import com.dqv5.soccer.mapper.SysUserMapper;
import com.dqv5.soccer.table.SysRoleTable;
import com.dqv5.soccer.table.SysUserTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查出用户对象
        SysUserTable sysUserTable = sysUserMapper.selectOne(Wrappers.query(SysUserTable.class).eq("account", username));
        if (sysUserTable == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }

        // 查出用户拥有的角色列表
        List<SysRoleTable> roles = sysRoleMapper.queryByUserId(sysUserTable.getUserId());
        // 构造权限集合
        Set<GrantedAuthority> auths = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleValue()))
                .collect(Collectors.toSet());
        // 构造一个UserDetails对象返回，至少需要这些参数：用户名、密码、权限集合
        return new AuthUser(sysUserTable.getUserId(), sysUserTable.getAccount(), sysUserTable.getPassword(), sysUserTable.getNickName(), sysUserTable.getAvatarUrl(), sysUserTable.getGender(), auths);
    }
}
