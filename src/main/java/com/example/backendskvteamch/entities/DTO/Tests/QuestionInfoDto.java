package com.example.backendskvteamch.entities.DTO.Tests;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class QuestionInfoDto {
    private String data;

    private List<AnswerInfoDto> answers;
}
