package com.example.backendskvteamch.entities.DTO.Vacancies;

import com.example.backendskvteamch.entities.DTO.Tests.TestInfoDTO;
import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VacancyInfoDTO {
    private String name;
    private String description;
    private String type;
    private Boolean isOpen;
    private List<TestInfoDTO> tests;

    public VacancyInfoDTO(Vacancy vacancy) {
        this.name = vacancy.getName();
        this.description = vacancy.getDescription();
        this.type = vacancy.getType().name();
        this.isOpen = vacancy.getIsOpen();
        this.tests = vacancy.getTests().stream()
                .map(TestInfoDTO::new)
                .collect(Collectors.toList());
    }
}
