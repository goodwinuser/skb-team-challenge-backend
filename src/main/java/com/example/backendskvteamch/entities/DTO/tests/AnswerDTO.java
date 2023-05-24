package com.example.backendskvteamch.entities.DTO.tests;


import lombok.Builder;

@Builder
public record AnswerDTO(Long id,
                        String data,
                        Boolean isCorrect) {
}
