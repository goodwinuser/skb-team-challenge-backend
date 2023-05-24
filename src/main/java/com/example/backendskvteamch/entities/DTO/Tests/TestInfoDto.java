package com.example.backendskvteamch.entities.DTO.Tests;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestInfoDto {
    private String name;

    private List<QuestionInfoDto> questions;

}
