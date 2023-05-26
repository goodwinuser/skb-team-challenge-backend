package com.example.backendskvteamch.controllers;

import com.example.backendskvteamch.entities.DTO.Vacancies.VacancyInfoDTO;
import com.example.backendskvteamch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<List<VacancyInfoDTO>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getVacancies().stream().map(VacancyInfoDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyInfoDTO> getVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.getVacancy(id)));
    }
}
