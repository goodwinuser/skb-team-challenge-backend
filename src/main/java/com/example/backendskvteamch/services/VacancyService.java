package com.example.backendskvteamch.services;

import com.example.backendskvteamch.entities.Commons.VacancyType;
import com.example.backendskvteamch.entities.DTO.Vacancies.VacancyInfoDTO;
import com.example.backendskvteamch.entities.Vacancies.Tag;
import com.example.backendskvteamch.entities.Vacancies.Vacancy;
import com.example.backendskvteamch.repositories.TagRepository;
import com.example.backendskvteamch.repositories.TestRepository;
import com.example.backendskvteamch.repositories.UserRepository;
import com.example.backendskvteamch.repositories.VacancyRepository;
import com.example.backendskvteamch.utilities.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final TestService testService;
    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final TagRepository tagRepository;

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy getVacancy(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Не найдена вакансия с id %d", id)));
    }

    public Vacancy createVacancy(Long adminId, VacancyInfoDTO vacancyInfoDto) {
        var author = userService.getAdmin(adminId);

        var vacancy = new Vacancy();
        vacancy.setName(vacancyInfoDto.getName());
        vacancy.setDescription(vacancyInfoDto.getDescription());
        vacancy.setType(VacancyType.valueOf(vacancyInfoDto.getType()));
        vacancy.setIsOpen(vacancyInfoDto.getIsOpen());
        vacancy.setMinSalary(vacancyInfoDto.getMinSalary());
        vacancy.setMaxSalary(vacancyInfoDto.getMaxSalary());
        vacancy.setAuthor(author);
        var savedVacancy = vacancyRepository.save(vacancy);

        for (var tagName : vacancyInfoDto.getTags()) {
            var tag = new Tag();
            tag.setName(tagName);
            tag.getVacancies().add(savedVacancy);

            savedVacancy.getTags().add(tagRepository.save(tag));
        }

        return vacancyRepository.save(savedVacancy);
    }

    public Vacancy updateVacancy(Long vacancyId, VacancyInfoDTO vacancyInfoDto) {
        var vacancy = getVacancy(vacancyId);

        vacancy.setName(vacancyInfoDto.getName());
        vacancy.setDescription(vacancyInfoDto.getDescription());
        vacancy.setType(VacancyType.valueOf(vacancyInfoDto.getType()));
        vacancy.setMinSalary(vacancyInfoDto.getMinSalary());
        vacancy.setMaxSalary(vacancyInfoDto.getMaxSalary());

        for (var oldTag : vacancy.getTags()) {
            oldTag.setVacancies(oldTag.getVacancies().stream().filter(x -> !Objects.equals(x.getId(), vacancy.getId())).collect(Collectors.toSet()));
            tagRepository.save(oldTag);
        }
        vacancy.getTags().clear();

        var savedVacancy = vacancyRepository.save(vacancy);

        for (var tagName : vacancyInfoDto.getTags()) {
            var tag = new Tag();
            tag.setName(tagName);
            tag.getVacancies().add(savedVacancy);

            savedVacancy.getTags().add(tagRepository.save(tag));
        }

        return vacancyRepository.save(savedVacancy);
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
        var vacancy = getVacancy(vacancyId);
        for(var user : vacancy.getUsers()){
            user.setVacancies(user.getVacancies().stream().filter(x-> !Objects.equals(x.getId(), vacancy.getId())).collect(Collectors.toSet()));
        }
        for(var tag : vacancy.getTags()){
            tag.setVacancies(tag.getVacancies().stream().filter(x-> !Objects.equals(x.getId(), vacancy.getId())).collect(Collectors.toSet()));
        }
        for(var test : vacancy.getTests()){
            test.setVacancies(test.getVacancies().stream().filter(x-> !Objects.equals(x.getId(), vacancy.getId())).collect(Collectors.toSet()));
        }

        vacancy.getUsers().clear();
        vacancy.getTags().clear();
        vacancy.getTests().clear();

        vacancyRepository.delete(vacancy);
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

    public Vacancy attachUser(Long vacancyId, Long userId) {
        var user = userService.getUser(userId);
        var vacancy = getVacancy(vacancyId);

        user.getVacancies().add(vacancy);
        userRepository.save(user);

        vacancy.getUsers().add(user);
        return vacancyRepository.save(vacancy);
    }

    public Vacancy removeUser(Long vacancyId, Long userId) {
        var user = userService.getUser(userId);
        var vacancy = getVacancy(vacancyId);

        if (vacancy.getUsers().stream().anyMatch(x -> x.getId().equals(user.getId()))) {
            user.getVacancies().remove(vacancy);
            userRepository.save(user);

            vacancy.getUsers().remove(user);
        }
        return vacancyRepository.save(vacancy);
    }
}