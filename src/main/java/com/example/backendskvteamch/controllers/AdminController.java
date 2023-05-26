package com.example.backendskvteamch.controllers;


import com.example.backendskvteamch.entities.DTO.Candidates.CandidateDTO;
import com.example.backendskvteamch.entities.DTO.Candidates.MeetDTO;
import com.example.backendskvteamch.entities.DTO.Candidates.ProceedCandidateDTO;
import com.example.backendskvteamch.entities.DTO.Tests.TestInfoDTO;
import com.example.backendskvteamch.entities.DTO.Tests.TestVacancyLinkDTO;
import com.example.backendskvteamch.entities.DTO.Vacancies.VacancyInfoDTO;
import com.example.backendskvteamch.services.TestService;
import com.example.backendskvteamch.services.UserService;
import com.example.backendskvteamch.services.VacancyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final VacancyService vacancyService;

    private final UserService userService;

    private final TestService testService;
    //    1) админ может создать/редактировать/закрыть/удалить вакансию/стажировку.
    //    (возможно интеграция с условным hh.ru/тг и другими сервисами, чтобы вакансия появлялась и там сразу)
    //    в информацию о вакансии предлагаю внедрить систему тегов (как набор навыков на hh.ru)

    @PostMapping("/vacancy")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<VacancyInfoDTO> createVacancy(@RequestBody VacancyInfoDTO dto) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var admin = userService.getAdmin(userDetails.getUsername());

        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.createVacancy(admin.getId(), dto)));
    }

    @PatchMapping("/vacancy/{id}")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<VacancyInfoDTO> updateVacancy(@PathVariable Long id, @RequestBody VacancyInfoDTO dto) {
        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.updateVacancy(id, dto)));
    }


    @PostMapping("/vacancy/open/{id}")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<VacancyInfoDTO> openVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.openVacancy(id)));
    }

    @PostMapping("/vacancy/close/{id}")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<VacancyInfoDTO> closeVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.closeVacancy(id)));
    }

    //    Full delete
    @DeleteMapping("/vacancy/{vacancy_id}")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<String> removeVacancy(@PathVariable Long vacancy_id) {
        vacancyService.deleteVacancy(vacancy_id);
        return ResponseEntity.ok().build();
    }

    //    Add test to vacancy
    @PostMapping("/vacancy/link_test")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<VacancyInfoDTO> linkTestToVacancy(@RequestBody TestVacancyLinkDTO dto) {
        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.attachTest(dto.vacancy_id(), dto.test_id())));
    }

    //    Unlink test from vacancy
    @PostMapping("/vacancy/unlink_test")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<VacancyInfoDTO> unlinkTestFromVacancy(@RequestBody TestVacancyLinkDTO dto) {
        return ResponseEntity.ok(new VacancyInfoDTO(vacancyService.removeTest(dto.vacancy_id(), dto.test_id())));
    }

    //    2) просмотреть все отклики по созданным/открытым вакансиям
    //    (фильтрация списка кандидатов по вакансиям, на которые они подавались)
    @PostMapping("/candidates")
    @Operation(tags = "candidate-admin")
    public ResponseEntity<String> listCandidates() {
        return null;
    }

    //    3) просмотреть каждого кандидата подробно (посмотреть резюме/контакты кандидата,
    //    результаты пройденных тестов/список не пройденных тестов, все отклики на разные вакансии на платформы)
    //    если мы интегрируемся с другими платформами, то собираем и отклики с них
    @GetMapping("/candidate/{candidate_id}")
    @Operation(tags = "candidate-admin")
    public ResponseEntity<CandidateDTO> getCandidate(@PathVariable Long candidate_id) {
        return null;
    }


    @PostMapping("/candidate/apply")
    @Operation(tags = "candidate-admin")
    public ResponseEntity<String> applyCandidate(@RequestBody ProceedCandidateDTO dto) {
        return null;
    }

    //    8) отправить кандидата в "кадровый резерв" (его результаты, резюме и тд не теряются, а в своеобразном архиве)
    @PostMapping("/candidate/archive/{candidate_id}")
    @Operation(tags = "candidate-admin")
    public ResponseEntity<String> sendToArchive(@PathVariable Long candidate_id) {
        return null;
    }

    @PostMapping("/candidate/drop/{candidate_id}")
    @Operation(tags = "candidate-admin")
    public ResponseEntity<String> dropCandidate(@PathVariable Long candidate_id) {
        return null;
    }

    @PostMapping("/candidate/delete/{candidate_id}")
    @Operation(tags = "candidate-admin")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long candidate_id) {
        return null;
    }

    //    10) закрепить кандидата за определенным hr
    @PostMapping("/candidate/childerize/{candidate_id}")
    @Operation(tags = "candidate-admin")
    public ResponseEntity<String> addToChildren(@PathVariable Long candidate_id) {
        return null;
    }

    //    Добавить в отслеживаемые (типа, фавориты)
    @PostMapping("/candidate/favour/{candidate_id}")
    @Operation(tags = "candidate-admin")
    public ResponseEntity<String> addToFavorites(@PathVariable Long candidate_id) {
        return null;
    }

    //    4) создать/изменить/удалить тесты, которые пользователь может пройти (психотесты будут не удаляемые я думаю)
    @PostMapping("/test")
    @Operation(tags = "test-admin")
    public ResponseEntity<TestInfoDTO> createTest(@RequestBody TestInfoDTO testInfoDto) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var admin = userService.getAdmin(userDetails.getUsername());

        return ResponseEntity.ok(new TestInfoDTO(testService.createTest(admin.getId(), testInfoDto)));
    }


    @PatchMapping("/test/{test_id}")
    @Operation(tags = "test-admin")
    public ResponseEntity<TestInfoDTO> updateTest(@PathVariable Long test_id, @RequestBody TestInfoDTO testInfoDto) {
        return ResponseEntity.ok(new TestInfoDTO(testService.updateTest(test_id, testInfoDto)));
    }

    @DeleteMapping("/test/{test_id}")
    @Operation(tags = "test-admin")
    public ResponseEntity<TestInfoDTO> deleteTest(@PathVariable Long test_id) {
        testService.deleteTest(test_id);
        return ResponseEntity.ok().build();
    }

    //    5) админ может назначить кандидату собеседование на определенное время и дату
    //    (оно отобразится в общем календаре админов с доп инфой в описании -
    //    кто с кем, когда и тд, также там будет ссылка на чат/конференцию) - кандидат уведомляется о встрече
    @PostMapping("/meet")
    @Operation(tags = "meet-admin")
    public ResponseEntity<String> addMeet(@RequestBody MeetDTO dto) {
        return null;
    }

    @PatchMapping("/meet")
    @Operation(tags = "meet-admin")
    public ResponseEntity<String> rescheduleMeet(@RequestBody MeetDTO dto) {
        return null;
    }

    @DeleteMapping("/meet/{meet_id}")
    @Operation(tags = "meet-admin")
    public ResponseEntity<String> cancelMeet(@PathVariable Long meet_id) {
        return null;
    }

    //    6) подключить в личном кабинете информирование об новых откликах (тг бот, почта, смс)
    //    - реализуем в последний момент разработки если будет время
    @PostMapping("/trigger")
    @Operation(tags = "trigger-admin")
    public ResponseEntity<String> addTrigger() {
        return null;
    }

    @DeleteMapping("/trigger")
    @Operation(tags = "trigger-admin")
    public ResponseEntity<String> deleteTrigger() {
        return null;
    }



    //    9) просмотреть статистику по откликам и вакансиям во всей системе
    @GetMapping("/stats")
    @Operation(tags = "stat-admin")
    public ResponseEntity<String> getStats() {
        return null;
    }


}
