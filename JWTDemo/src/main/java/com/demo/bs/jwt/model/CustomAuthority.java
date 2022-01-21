package com.demo.bs.jwt.model;

import org.springframework.security.core.GrantedAuthority;

public class CustomAuthority implements GrantedAuthority {
    private final String role;

    public CustomAuthority(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
