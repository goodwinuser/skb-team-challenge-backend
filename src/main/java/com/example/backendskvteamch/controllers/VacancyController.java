package com.example.backendskvteamch.controllers;

import com.example.backendskvteamch.entities.DTO.Vacancies.VacancyInfoDto;
import com.example.backendskvteamch.entities.DTO.Vacancies.VacancyTestInfoDto;
import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import com.example.backendskvteamch.services.UserService;
import com.example.backendskvteamch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<VacancyInfoDto>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getVacancies().stream().map(VacancyController::convert).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyInfoDto> getVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(convert(vacancyService.getVacancy(id)));
    }

    @PostMapping
    public ResponseEntity<VacancyInfoDto> createVacancy(@RequestBody VacancyInfoDto dto) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var admin = userService.getAdmin(userDetails.getUsername());

        return ResponseEntity.ok(convert(vacancyService.createVacancy(admin.getId(), dto)));
    }

    @PostMapping("/open/{id}")
    public ResponseEntity<VacancyInfoDto> openVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(convert(vacancyService.openVacancy(id)));
    }

    @PostMapping("/close/{id}")
    public ResponseEntity<VacancyInfoDto> closeVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(convert(vacancyService.closeVacancy(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacancyInfoDto> updateVacancy(@PathVariable Long id, @RequestBody VacancyInfoDto dto) {
        return ResponseEntity.ok(convert(vacancyService.updateVacancy(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VacancyInfoDto> deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteVacancy(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test/add")
    public ResponseEntity<VacancyInfoDto> addTest(@RequestBody VacancyTestInfoDto dto) {
        return ResponseEntity.ok(convert(vacancyService.attachTest(dto.getVacancyId(), dto.getTestId())));
    }

    @PostMapping("/test/remove")
    public ResponseEntity<VacancyInfoDto> removeTest(@RequestBody VacancyTestInfoDto dto) {
        return ResponseEntity.ok(convert(vacancyService.removeTest(dto.getVacancyId(), dto.getTestId())));
    }

    public static VacancyInfoDto convert(Vacancy vacancy) {
        return VacancyInfoDto.builder()
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .type(vacancy.getType().name())
                .isOpen(vacancy.getIsOpen())
                .tests(vacancy.getTests().stream()
                        .map(TestController::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}
