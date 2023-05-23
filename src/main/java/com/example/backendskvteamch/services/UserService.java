package com.example.backendskvteamch.services;

import com.example.backendskvteamch.entities.Users.Admin;
import com.example.backendskvteamch.repositories.AdminRepository;
import com.example.backendskvteamch.utilities.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AdminRepository adminRepository;

    public Admin getAdmin(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Не найден админ с id %d", id)));
    }
}
