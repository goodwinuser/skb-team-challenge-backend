package com.example.backendskvteamch.entities.Commons;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    UNKNOWN_USER,
    UNVERIFIED_USER,
    VERIFIED_USER,
    DEPARTMENT_HEAD,
    HR_ADMIN,
    HR_HEAD,
    SUPPORT,
    CUSTOMER_REPR,
    SUPERADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}