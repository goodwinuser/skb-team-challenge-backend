package com.example.backendskvteamch.entities.DTO.Tests;

import lombok.Builder;

@Builder
public record TestVacancyLinkDTO(Long test_id, Long vacancy_id) {
}
