package com.example.backendskvteamch.controllers;

import com.example.backendskvteamch.entities.DTO.Tests.AnswerInfoDto;
import com.example.backendskvteamch.entities.DTO.Tests.QuestionInfoDto;
import com.example.backendskvteamch.entities.DTO.Tests.TestInfoDto;
import com.example.backendskvteamch.entities.Tests.Test;
import com.example.backendskvteamch.services.TestService;
import com.example.backendskvteamch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<TestInfoDto>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests().stream().map(TestController::convert).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestInfoDto> getTest(@PathVariable Long id) {
        return ResponseEntity.ok(convert(testService.getTest(id)));
    }

    @PostMapping
    public ResponseEntity<TestInfoDto> createTest(@RequestBody TestInfoDto testInfoDto) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var admin = userService.getAdmin(userDetails.getUsername());

        return ResponseEntity.ok(convert(testService.createTest(admin.getId(), testInfoDto)));
    }

    @PutMapping("/{testId}")
    public ResponseEntity<TestInfoDto> createTest(@PathVariable Long testId, @RequestBody TestInfoDto testInfoDto) {
        return ResponseEntity.ok(convert(testService.updateTest(testId, testInfoDto)));
    }

    @DeleteMapping("/{testId}")
    public ResponseEntity<TestInfoDto> createTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return ResponseEntity.ok().build();
    }

    public static TestInfoDto convert(Test test) {
        return TestInfoDto.builder()
                .name(test.getName())
                .questions(test.getQuestions().stream()
                        .map(x -> QuestionInfoDto.builder()
                                .data(x.getData())
                                .answers(x.getAnswers().stream()
                                        .map(y -> AnswerInfoDto.builder()
                                                .data(y.getData())
                                                .isCorrect(y.getIsCorrect())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
