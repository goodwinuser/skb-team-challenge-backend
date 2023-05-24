package com.example.backendskvteamch.entities.DTO.tests;

import lombok.Builder;
import lombok.NonNull;

import java.util.Set;

@Builder
public record TestDTO(@NonNull Long test_id,
                      @NonNull Long category_id,
                      @NonNull Set<QuestionDTO> questions) {

}
