package az.spring.bookstore.request;

import az.spring.bookstore.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRequest {

    private Long id;
    private String name;
    private BookStatus status;
    private Long studentId;
    private Long authorId;

}