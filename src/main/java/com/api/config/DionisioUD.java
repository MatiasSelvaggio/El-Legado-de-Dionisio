package com.api.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DionisioUD implements UserDetails {

    private final String username;
    private final boolean enable;
    @Getter
    private final String id;
    private final List<DionisioAuthority> authorities;

    public DionisioUD(String id, String username, boolean enable, List<?> roles){
        this.username = username;
        this.enable = enable;
        this.id = id;
        this.authorities = (roles != null ? roles : Collections.emptyList())
                .stream()
                .filter(rol -> rol instanceof String)
                .map(rol -> new DionisioAuthority((String) rol))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
