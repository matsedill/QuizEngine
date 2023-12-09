package engine.dto;

import java.time.LocalDateTime;

public record QuizCompleteOutput(long id, LocalDateTime completedAt) {
}
