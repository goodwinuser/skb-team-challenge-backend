package com.example.backendskvteamch.services;

import com.example.backendskvteamch.entities.DTO.Tests.TestInfoDto;
import com.example.backendskvteamch.entities.Tests.Answer;
import com.example.backendskvteamch.entities.Tests.Question;
import com.example.backendskvteamch.entities.Tests.Test;
import com.example.backendskvteamch.repositories.AnswerRepository;
import com.example.backendskvteamch.repositories.QuestionRepository;
import com.example.backendskvteamch.repositories.TestRepository;
import com.example.backendskvteamch.repositories.TestResultRepository;
import com.example.backendskvteamch.utilities.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final TestResultRepository testResultRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserService userService;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTest(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Не найден тест с id %d", id)));
    }

    public Test createTest(Long userId, TestInfoDto requestDto) {
        var admin = userService.getAdmin(userId);

        var test = new Test();
        test.setName(requestDto.getName());
        test.setCreator(admin);

        var savedTest = testRepository.save(test);

        for (var questionInfo : requestDto.getQuestions()) {
            var question = new Question();
            question.setData(questionInfo.getData());
            question.setTest(savedTest);
            var savedQuestion = questionRepository.save(question);

            for (var answerInfo : questionInfo.getAnswers()) {
                var answer = new Answer();
                answer.setData(answerInfo.getData());
                answer.setIsCorrect(answerInfo.getIsCorrect());
                answer.setQuestion(savedQuestion);

                savedQuestion.getAnswers().add(answerRepository.save(answer));
            }

            savedTest.getQuestions().add(questionRepository.save(savedQuestion));
        }

        return testRepository.save(savedTest);
    }

    public Test updateTest(Long testId, TestInfoDto requestDto) {
        var test = getTest(testId);
        test.setName(requestDto.getName());
        test.getQuestions().clear();

        var savedTest = testRepository.save(test);

        for (var questionInfo : requestDto.getQuestions()) {
            var question = new Question();
            question.setData(questionInfo.getData());
            question.setTest(savedTest);
            var savedQuestion = questionRepository.save(question);

            for (var answerInfo : questionInfo.getAnswers()) {
                var answer = new Answer();
                answer.setData(answerInfo.getData());
                answer.setIsCorrect(answerInfo.getIsCorrect());
                answer.setQuestion(savedQuestion);

                savedQuestion.getAnswers().add(answerRepository.save(answer));
            }

            savedTest.getQuestions().add(questionRepository.save(savedQuestion));
        }

        return testRepository.save(savedTest);
    }

    public void deleteTest(Long testId) {
        testRepository.delete(getTest(testId));
    }

}
