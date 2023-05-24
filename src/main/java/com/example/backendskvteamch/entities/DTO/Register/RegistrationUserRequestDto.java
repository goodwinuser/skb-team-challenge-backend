package com.example.backendskvteamch.entities.DTO.Register;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationUserRequestDto {

    private String username;
    private String fio;
    private String email;
    private String password;

}
