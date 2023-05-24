package com.example.backendskvteamch.controllers;


import com.example.backendskvteamch.entities.DTO.candidates.CandidateDTO;
import com.example.backendskvteamch.entities.DTO.candidates.MeetDTO;
import com.example.backendskvteamch.entities.DTO.candidates.ProceedCandidateDTO;
import com.example.backendskvteamch.entities.DTO.tests.TestDTO;
import com.example.backendskvteamch.entities.DTO.tests.TestVacancyLinkDTO;
import com.example.backendskvteamch.entities.DTO.vacancies.VacancyDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    //    1) админ может создать/редактировать/закрыть/удалить вакансию/стажировку.
    //    (возможно интеграция с условным hh.ru/тг и другими сервисами, чтобы вакансия появлялась и там сразу)
    //    в информацию о вакансии предлагаю внедрить систему тегов (как набор навыков на hh.ru)
    @PostMapping("/vacancy")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<String> createVacancy(@RequestBody VacancyDTO dto) {
        return null;
    }

    @PatchMapping("/vacancy")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<String> updateVacancy(@RequestBody VacancyDTO dto) {
        return null;
    }

    //    Archive
    @PutMapping("/vacancy/{vacancy_id}")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<String> dropVacancy(@PathVariable Long vacancy_id) {
        return null;
    }

    //    Full delete
    @DeleteMapping("/vacancy/{vacancy_id}")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<String> removeVacancy(@PathVariable Long vacancy_id) {
        return null;
    }

    //    Add test to vacancy
    @PostMapping("/vacancy/link_test")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<String> linkTestToVacancy(@RequestBody TestVacancyLinkDTO dto) {
        return null;
    }

    //    Unlink test from vacancy
    @PostMapping("/vacancy/unlink_test")
    @Operation(tags = "vacancy-admin")
    public ResponseEntity<String> unlinkTestFromVacancy(@RequestBody TestVacancyLinkDTO dto) {
        return null;
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
    public ResponseEntity<String> createTest(@RequestBody TestDTO dto) {
        return null;
    }

    @PatchMapping("/test")
    @Operation(tags = "test-admin")
    public ResponseEntity<String> updateTest(@RequestBody TestDTO dto) {
        return null;
    }

    @DeleteMapping("/test/{test_id}")
    @Operation(tags = "test-admin")
    public ResponseEntity<String> deleteTest(@PathVariable Long test_id) {
        return null;
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

    //    7) пообщаться с кандидатом или другими hr/коллегами через защищенный чат на нашей платформе
    //    (большим плюсом будет еще и видео-конференции на нашей платформе)


    //    9) просмотреть статистику по откликам и вакансиям во всей системе
    @GetMapping("/stats")
    @Operation(tags = "stat-admin")
    public ResponseEntity<String> getStats() {
        return null;
    }


}
