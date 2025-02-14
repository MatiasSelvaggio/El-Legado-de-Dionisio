package com.api.config;

import org.springframework.security.core.GrantedAuthority;

public record DionisioAuthority(String authority) implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return authority;
    }
}
