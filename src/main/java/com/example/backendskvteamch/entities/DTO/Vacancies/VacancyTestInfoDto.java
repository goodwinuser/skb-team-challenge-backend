package com.example.backendskvteamch.entities.DTO.Vacancies;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VacancyTestInfoDTO {
    private Long vacancyId;
    private Long testId;
}
