package com.example.backendskvteamch.controllers;

import com.example.backendskvteamch.entities.DTO.Users.ProfileInfoDTO;
import com.example.backendskvteamch.entities.DTO.Vacancies.VacancyInfoDTO;
import com.example.backendskvteamch.entities.DTO.Vacancies.VacancyLinkDTO;
import com.example.backendskvteamch.services.UserService;
import com.example.backendskvteamch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final VacancyService vacancyService;
    private final UserService userService;

    @PostMapping("/vacancy/attach")
    public ResponseEntity<VacancyInfoDTO> attachVacancy(@RequestBody VacancyLinkDTO requestDTO) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.getUser(userDetails.getUsername());

        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.attachUser(requestDTO.getVacancyId(), user.getId())));
    }

    @PostMapping("/vacancy/delete")
    public ResponseEntity<VacancyInfoDTO> delete(@RequestBody VacancyLinkDTO requestDTO) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.getUser(userDetails.getUsername());

        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.removeUser(requestDTO.getVacancyId(), user.getId())));
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileInfoDTO> getProfile() {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.getUser(userDetails.getUsername());

        return ResponseEntity.ok(new ProfileInfoDTO(userService.getUser(user.getId())));
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileInfoDTO> createProfile(@RequestBody ProfileInfoDTO requestDTO) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.getUser(userDetails.getUsername());

        return ResponseEntity.ok(new ProfileInfoDTO(userService.setProfile(user.getId(), requestDTO)));
    }

    @PatchMapping("/profile")
    public ResponseEntity<ProfileInfoDTO> updateProfile(@RequestBody ProfileInfoDTO requestDTO) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userService.getUser(userDetails.getUsername());

        return ResponseEntity.ok(new ProfileInfoDTO(userService.setProfile(user.getId(), requestDTO)));
    }
}
