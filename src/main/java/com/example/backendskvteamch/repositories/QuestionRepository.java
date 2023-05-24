package com.example.backendskvteamch.repositories;

import com.example.backendskvteamch.entities.Tests.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
