package com.example.backendskvteamch.services;

import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import com.example.backendskvteamch.repositories.VacancyRepository;
import com.example.backendskvteamch.utilities.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public Vacancy getVacancy(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Не найдена вакансия с id %d", id)));
    }
}