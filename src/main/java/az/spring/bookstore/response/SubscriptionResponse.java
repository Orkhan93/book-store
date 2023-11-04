package az.spring.bookstore.response;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubscriptionResponse {

    private Long id;

    @JsonIgnore
    private Student student;

    @JsonIgnore
    private Author author;

}