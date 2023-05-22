package com.example.backendskvteamch.entities.DTO.Auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUNameRequest {
    private String username;

    private String password;
}
