package com.example.backendskvteamch.entities.DTO.Vacancies;

import com.example.backendskvteamch.entities.DTO.Tests.TestInfoDto;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VacancyInfoDto {
    private String name;
    private String description;
    private String type;
    private Boolean isOpen;
    private List<TestInfoDto> tests;
}
