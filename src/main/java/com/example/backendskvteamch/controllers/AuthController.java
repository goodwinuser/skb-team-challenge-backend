package com.example.backendskvteamch.controllers;


import com.example.backendskvteamch.entities.DTO.Auth.AuthResponse;
import com.example.backendskvteamch.entities.DTO.Auth.AuthUNameRequest;
import com.example.backendskvteamch.entities.DTO.Register.RegistrationUserRequestDto;
import com.example.backendskvteamch.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/users/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegistrationUserRequestDto request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/users/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthUNameRequest request) {
        return ResponseEntity.ok(service.authenticateUser(request));
    }

    @PostMapping("/admins/register")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegistrationUserRequestDto request) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }

    @PostMapping("/admins/login")
    public ResponseEntity<AuthResponse> authenticateAdmin(@RequestBody AuthUNameRequest request) {
        return ResponseEntity.ok(service.authenticateAdmin(request));
    }
}
