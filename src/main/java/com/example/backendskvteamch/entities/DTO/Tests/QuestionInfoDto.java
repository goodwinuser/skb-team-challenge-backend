package com.example.backendskvteamch.entities.DTO.Tests;


import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionInfoDto {
    private String data;

    private List<AnswerInfoDto> answers;
}
