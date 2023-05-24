package com.example.backendskvteamch.entities.DTO.vacancies;

import lombok.Builder;

@Builder
public record VacancyDTO(String name, String description, String type) {
}
