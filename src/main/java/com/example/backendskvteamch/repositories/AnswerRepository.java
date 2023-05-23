package com.example.backendskvteamch.repositories;

import com.example.backendskvteamch.entities.Tests.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
