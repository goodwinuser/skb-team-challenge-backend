package com.example.backendskvteamch.repositories;

import com.example.backendskvteamch.entities.Tests.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
