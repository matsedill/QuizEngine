package engine.services;

import engine.dto.*;
import engine.repositories.QuizCompleteRepository;
import engine.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizzes;

    @Autowired
    QuizCompleteRepository quizCompletes;

    public QuizService(QuizRepository quizzes) {
        this.quizzes = quizzes;
    }

    public QuizOutput createQuiz(QuizInput input) {
        // Create the "unique" id
        Quiz quiz = new Quiz(
            input.title(),
            input.text(),
            input.options(),
            input.answer(),
            SecurityContextHolder.getContext().getAuthentication().getName()
        );
        quizzes.save(quiz);
        return quizToOutput(quiz);
    }

    public QuizOutput getQuizById(Long id) {
        var quiz = quizzes.findById(id).orElse(null);
        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return quizToOutput(quiz);
    }

    public Page<QuizOutput> getQuizzes(int pageNumber) {
        var fetchedQuizzes = quizzes.findAll(PageRequest.of(pageNumber, 10));
        return fetchedQuizzes
                .map(this::quizToOutput);
    }

    public QuizAnswerOutput  answerQuestion(Long quizId, QuizAnswer answer) {
        var quizToAnswer = quizzes.findById(quizId).orElse(null);
        if (quizToAnswer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        QuizAnswerOutput quizAnswerOutput;
        if(quizToAnswer.getAnswer() == null &&  answer.answer().isEmpty()
                || quizToAnswer.getAnswer()!= null
                && new HashSet<>(quizToAnswer.getAnswer()).containsAll(answer.answer())
                && quizToAnswer.getAnswer().size() == answer.answer().size()) {
            quizAnswerOutput = new QuizAnswerOutput(true, "Congratulations, you're right!");
            QuizComplete quizComplete = new QuizComplete(quizId, LocalDateTime.now(), getUserName());
            quizCompletes.save(quizComplete);
        } else  {
            quizAnswerOutput = new QuizAnswerOutput(false, "Wrong answer! Please, try again.");
        }

        return quizAnswerOutput;
    }

    private QuizOutput quizToOutput(Quiz quiz) {
        return new QuizOutput(
                quiz.getTitle(), quiz.getText(), quiz.getOptions(),quiz.getId()
                );

    }

    public void deleteQuizById(Long id) {
        var authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        quizzes.findById(id).ifPresentOrElse(quiz -> {
            if (!quiz.getAuthor().equals(authenticatedUserName)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
            quizzes.delete(quiz);
        },
        () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });


    }

    public Page<QuizCompleteOutput> getCompletedQuizzes(int pageNumber) {
        var sortOrder = Sort.by(new Sort.Order(Sort.Direction.DESC, "completedAt"));
        var pageRequest = PageRequest.of(pageNumber, 10, sortOrder);
        var fetchedQuizzes = quizCompletes.findByUsername(getUserName(), pageRequest);
        return fetchedQuizzes
                .map(this::quizCompleteToOutput);
    }

    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    private QuizCompleteOutput quizCompleteToOutput(QuizComplete quizComplete) {
        return new QuizCompleteOutput(quizComplete.getQuizId(), quizComplete.getCompletedAt());
    }
}
