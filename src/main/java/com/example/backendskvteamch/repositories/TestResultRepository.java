package com.example.backendskvteamch.repositories;

import com.example.backendskvteamch.entities.Tests.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
