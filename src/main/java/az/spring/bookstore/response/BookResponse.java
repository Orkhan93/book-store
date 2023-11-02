package az.spring.bookstore.response;

import az.spring.bookstore.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookResponse {

    private Long id;
    private String name;
    private BookStatus status;
    private Long authorId;

}