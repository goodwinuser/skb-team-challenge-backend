package com.example.backendskvteamch.services;

import com.example.backendskvteamch.entities.Commons.VacancyType;
import com.example.backendskvteamch.entities.DTO.Vacancies.VacancyInfoDto;
import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import com.example.backendskvteamch.repositories.TestRepository;
import com.example.backendskvteamch.repositories.VacancyRepository;
import com.example.backendskvteamch.utilities.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final TestService testService;
    private final TestRepository testRepository;

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy getVacancy(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Не найдена вакансия с id %d", id)));
    }

    public Vacancy createVacancy(Long adminId, VacancyInfoDto vacancyInfoDto) {
        var author = userService.getAdmin(adminId);

        var vacancy = new Vacancy();
        vacancy.setName(vacancyInfoDto.getName());
        vacancy.setDescription(vacancyInfoDto.getDescription());
        vacancy.setType(VacancyType.valueOf(vacancyInfoDto.getType()));
        vacancy.setIsOpen(vacancyInfoDto.getIsOpen());
        vacancy.setAuthor(author);

        return vacancyRepository.save(vacancy);
    }

    public Vacancy updateVacancy(Long vacancyId, VacancyInfoDto vacancyInfoDto) {
        var vacancy = getVacancy(vacancyId);

        vacancy.setName(vacancyInfoDto.getName());
        vacancy.setDescription(vacancyInfoDto.getDescription());
        vacancy.setType(VacancyType.valueOf(vacancyInfoDto.getType()));

        return vacancyRepository.save(vacancy);
    }

    public Vacancy openVacancy(Long vacancyId) {
        var vacancy = getVacancy(vacancyId);
        vacancy.setIsOpen(true);
        return vacancyRepository.save(vacancy);
    }

    public Vacancy closeVacancy(Long vacancyId) {
        var vacancy = getVacancy(vacancyId);
        vacancy.setIsOpen(false);
        return vacancyRepository.save(vacancy);
    }

    public void deleteVacancy(Long vacancyId) {
        vacancyRepository.delete(getVacancy(vacancyId));
    }

    public Vacancy attachTest(Long vacancyId, Long testId) {
        var test = testService.getTest(testId);
        var vacancy = getVacancy(vacancyId);

        test.getVacancies().add(vacancy);
        testRepository.save(test);

        vacancy.getTests().add(test);
        return vacancyRepository.save(vacancy);
    }

    public Vacancy removeTest(Long vacancyId, Long testId) {
        var test = testService.getTest(testId);
        var vacancy = getVacancy(vacancyId);

        if (vacancy.getTests().stream().anyMatch(x -> x.getId().equals(test.getId()))) {
            test.getVacancies().remove(vacancy);
            testRepository.save(test);

            vacancy.getTests().remove(test);
        }
        return vacancyRepository.save(vacancy);
    }
}