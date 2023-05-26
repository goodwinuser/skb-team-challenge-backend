package com.example.backendskvteamch.controllers;

import com.example.backendskvteamch.entities.DTO.Tests.TestInfoDTO;
import com.example.backendskvteamch.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;


    @GetMapping
    public ResponseEntity<List<TestInfoDTO>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests().stream().map(TestInfoDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestInfoDTO> getTest(@PathVariable Long id) {
        return ResponseEntity.ok(new TestInfoDTO(testService.getTest(id)));
    }
}
