package com.example.backendskvteamch.repositories;

import com.example.backendskvteamch.entities.Vacancies.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
