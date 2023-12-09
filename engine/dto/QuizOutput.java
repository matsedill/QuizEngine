package engine.dto;

import java.util.List;

public record QuizOutput(String title, String text, List<String> options, long id) { }
