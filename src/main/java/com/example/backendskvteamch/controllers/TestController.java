package com.example.backendskvteamch.controllers;

import com.example.backendskvteamch.entities.DTO.Tests.AnswerInfoDto;
import com.example.backendskvteamch.entities.DTO.Tests.QuestionInfoDto;
import com.example.backendskvteamch.entities.DTO.Tests.TestInfoDto;
import com.example.backendskvteamch.entities.Tests.Test;
import com.example.backendskvteamch.services.TestService;
import com.example.backendskvteamch.services.UserService;
import com.example.backendskvteamch.utilities.AuthorityAnnotations.AdminAuth;
import lombok.RequiredArgsConstructor;
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

    @AdminAuth
    @GetMapping
    public List<TestInfoDto> getAllTests() {
        return testService.getAllTests().stream().map(this::convert).collect(Collectors.toList());
    }

    @AdminAuth
    @GetMapping("/{id}")
    public TestInfoDto getTest(@PathVariable Long id) {
        return convert(testService.getTest(id));
    }

    @AdminAuth
    @PostMapping
    public TestInfoDto createTest(@RequestBody TestInfoDto testInfoDto) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var admin = userService.getAdmin(userDetails.getUsername());

        return convert(testService.createTest(admin.getId(), testInfoDto));
    }

    @AdminAuth
    @PutMapping("/{testId}")
    public TestInfoDto createTest(@PathVariable Long testId, @RequestBody TestInfoDto testInfoDto) {
        return convert(testService.updateTest(testId, testInfoDto));
    }

    @AdminAuth
    @DeleteMapping("/{testId}")
    public TestInfoDto createTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return null;
    }

    private TestInfoDto convert(Test test) {
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
