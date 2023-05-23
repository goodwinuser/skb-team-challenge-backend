package com.example.backendskvteamch.entities.DTO.Tests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CreateTestRequestDto {
    private String name;

    private List<QuestionInfoDto> questions;

}
