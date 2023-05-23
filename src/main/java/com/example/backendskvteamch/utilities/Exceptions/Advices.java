package com.example.backendskvteamch.utilities.Exceptions;

import com.example.backendskvteamch.entities.DTO.System.ExceptionMessageDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Log4j2
@RestControllerAdvice
public class Advices {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessageDto notFound(NotFoundException ex) {
        log.error(ex.getMessage());

        return ExceptionMessageDto.builder()
                .errors(List.of(ex.getMessage()))
                .build();
    }
}
