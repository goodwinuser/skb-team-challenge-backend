package com.example.backendskvteamch.entities.DTO.Vacancies;

import com.example.backendskvteamch.entities.Tests.Test;
import com.example.backendskvteamch.entities.Users.User;
import com.example.backendskvteamch.entities.Vacancies.Tag;
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
    private Long id;
    private String name;
    private String description;
    private String type;
    private Boolean isOpen;
    private Integer minSalary;
    private Integer maxSalary;
    private List<Long> tests;
    private List<String> tags;
    private List<Long> users;

    public VacancyInfoDTO(Vacancy vacancy) {
        this.id = vacancy.getId();
        this.name = vacancy.getName();
        this.description = vacancy.getDescription();
        this.type = vacancy.getType().name();
        this.isOpen = vacancy.getIsOpen();
        this.minSalary = vacancy.getMinSalary();
        this.maxSalary = vacancy.getMaxSalary();
        this.tests = vacancy.getTests().stream()
                .map(Test::getId)
                .collect(Collectors.toList());
        this.tags = vacancy.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
        this.users = vacancy.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }
}
