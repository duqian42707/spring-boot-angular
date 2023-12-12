package com.dqv5.soccer.security;

import com.dqv5.soccer.pojo.SysMenu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author duq
 * @date 2022/7/16
 */
@Getter
@Setter
public class AuthUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String account;
    private String password;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private List<SysMenu> menus;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.account = username;
        this.password = password;
        this.authorities = authorities;
    }

    public AuthUser(String userId, String account, String password, String nickName, String avatarUrl, String gender, List<SysMenu> menus, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.menus = menus;
        this.authorities = authorities;
    }

    //-----------implements from UserDetails start----------//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    //-----------implements from UserDetails end----------//

}
