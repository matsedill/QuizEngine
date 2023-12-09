package engine.dto;

import javax.validation.constraints.Min;
import java.util.List;

public record QuizAnswer(List<@Min(0) Integer> answer) {
}
