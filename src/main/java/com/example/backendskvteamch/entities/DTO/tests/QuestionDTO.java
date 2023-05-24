package com.example.backendskvteamch.entities.DTO.tests;

import lombok.Builder;

import java.util.Set;

@Builder
public record QuestionDTO(Long id,
                          String question_type,
                          String question_data,
                          Set<AnswerDTO> answers) {

}
