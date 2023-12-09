package engine.dto;

import javax.validation.constraints.*;
import java.util.List;

public record QuizInput(
        @NotBlank
        String title,
        @NotBlank
        String text,
        @NotNull
        @Size(min=2)
        List<String> options,
        List<@Min(0) Integer> answer) {
}
