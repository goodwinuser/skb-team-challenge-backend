package com.example.backendskvteamch.services;

import com.example.backendskvteamch.entities.Users.Admin;
import com.example.backendskvteamch.entities.Users.User;
import com.example.backendskvteamch.repositories.AdminRepository;
import com.example.backendskvteamch.repositories.UserRepository;
import com.example.backendskvteamch.utilities.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Не найден пользователь с id %d", id)));
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("Не найден пользователь с username %s", username)));
    }

    public Admin getAdmin(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Не найден админ с id %d", id)));
    }

    public Admin getAdmin(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("Не найден админ с username %s", username)));
    }
}
