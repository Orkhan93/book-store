package az.spring.bookstore.wrapper;

import az.spring.bookstore.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookWrapper {

    private Long id;
    private String name;
    private BookStatus status;
    private Long authorId;

}