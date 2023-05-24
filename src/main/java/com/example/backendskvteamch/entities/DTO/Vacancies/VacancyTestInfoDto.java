package com.example.backendskvteamch.entities.DTO.Vacancies;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VacancyTestInfoDto {
    private Long vacancyId;
    private Long testId;
}
