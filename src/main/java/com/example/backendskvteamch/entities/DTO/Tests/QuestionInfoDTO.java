package com.example.backendskvteamch.entities.DTO.Tests;


import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionInfoDTO {
    private Long id;

    private String data;

    private List<AnswerInfoDTO> answers;
}
