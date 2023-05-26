package com.example.backendskvteamch.utilities.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasAnyRole(T(com.example.backendskvteamch.entities.Commons.Role).USER, " +
        "T(com.example.backendskvteamch.entities.Commons.Role).UNVERIFIED_USER)")
public @interface UserAuth {
}
