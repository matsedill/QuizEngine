package engine.dto;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String author;

    String title;
    String text;
    @ElementCollection(fetch = FetchType.EAGER )
    List<String> options;

    @ElementCollection(fetch = FetchType.EAGER )
    @Fetch(value = FetchMode.SUBSELECT)
    List<Integer> answer;

    public Quiz() { }
    public Quiz(String title, String text, List<String> options, List<Integer> answer, String author) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.author= author;
    }

    public String getTitle(){
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
       return options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }
}
