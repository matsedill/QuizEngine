package engine.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class QuizComplete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quizId;
    private LocalDateTime completedAt;
    private String username;

    public QuizComplete(Long id, LocalDateTime completedAt, String username) {
        this.quizId = id;
        this.completedAt = completedAt;
        this.username = username;
    }

    public QuizComplete() {

    }

    public LocalDateTime getCompletedAt(){
        return this.completedAt;
    }

    public Long getQuizId() {
        return quizId;
    }
}
