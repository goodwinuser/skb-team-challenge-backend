package com.example.backendskvteamch.repositories;

import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}