package com.example.backendskvteamch.entities.DTO.System;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ExceptionMessageDto {
    private List<String> errors;
}
