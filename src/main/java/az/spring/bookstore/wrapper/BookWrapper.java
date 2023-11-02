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
    private Long studentId;

    public BookWrapper(Long id, String name, BookStatus status, Long authorId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.authorId = authorId;
    }

}