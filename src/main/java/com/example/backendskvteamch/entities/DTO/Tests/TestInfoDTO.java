package com.example.backendskvteamch.entities.DTO.Tests;

import com.example.backendskvteamch.entities.Tests.Test;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestInfoDTO {
    private Long id;
    private String name;
    private List<QuestionInfoDTO> questions;

    public TestInfoDTO(Test test) {
        this.id = test.getId();
        this.name = test.getName();
        this.questions = test.getQuestions().stream()
                .map(x -> QuestionInfoDTO.builder()
                        .data(x.getData())
                        .answers(x.getAnswers().stream()
                                .map(y -> AnswerInfoDTO.builder()
                                        .data(y.getData())
                                        .isCorrect(y.getIsCorrect())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

    }
}
