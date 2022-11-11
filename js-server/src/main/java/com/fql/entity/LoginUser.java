package com.fql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Qian
 */
public class LoginUser implements UserDetails  {
    /**
     * 封装一个用户类 用于登录校
     * 通过实现userDetails类封装本次请求的用户信息
      */
    private UserModel userModel ;

    private List<String> permissions ;

    //权限存储
    @JsonIgnore
    private List<GrantedAuthority> authorities ;



    public LoginUser() {
    }

    public LoginUser(UserModel userModel,List<String> permissions) {
        this.userModel = userModel;
        this.permissions = permissions;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null){
            return authorities ;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return userModel.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return userModel.getUserName();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
