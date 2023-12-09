package engine;

import engine.dto.*;
import engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class QuizController {

    @Autowired
    QuizService service;

    @PostMapping("/api/quizzes")
    public ResponseEntity<QuizOutput> createQuiz(@Valid @RequestBody QuizInput newQuiz) {
        return ResponseEntity.ok().body(service.createQuiz(newQuiz));
    }

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<QuizOutput> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getQuizById(id));
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable Long id) {
        service.deleteQuizById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/api/quizzes")
    public ResponseEntity<Page<QuizOutput>> getQuizzes(@RequestParam(value="page", required = false, defaultValue="0") int pageNumber) {
        return ResponseEntity.ok(service.getQuizzes(pageNumber));
    }
    @GetMapping("/api/quizzes/completed")
    public ResponseEntity<Page<QuizCompleteOutput>> getCompletedQuizzes(@RequestParam(value="page", required = false, defaultValue="0") int pageNumber) {
        return ResponseEntity.ok(service.getCompletedQuizzes(pageNumber));
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseEntity<QuizAnswerOutput> answerQuiz(@PathVariable long id , @Valid @RequestBody QuizAnswer answer) {
        return ResponseEntity.ok(service.answerQuestion(id, answer));
    }

}
