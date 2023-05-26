package com.example.backendskvteamch.utilities.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasAnyAuthority(T(com.example.backendskvteamch.entities.Commons.Role).ADMIN, T(com.example.backendskvteamch.entities.Commons.Role).CUSTOMER_REPR,T(com.example.backendskvteamch.entities.Commons.Role).DEPARTMENT_HEAD,T(com.example.backendskvteamch.entities.Commons.Role).SUPERADMIN,T(com.example.backendskvteamch.entities.Commons.Role).HR_ADMIN,T(com.example.backendskvteamch.entities.Commons.Role).HR_HEAD,T(com.example.backendskvteamch.entities.Commons.Role).SUPPORT)")
public @interface AdminAuth {
}
