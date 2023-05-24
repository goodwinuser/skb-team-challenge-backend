package com.example.backendskvteamch.entities.DTO.Tests;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnswerInfoDto {
    private String data;

    private Boolean isCorrect;
}
