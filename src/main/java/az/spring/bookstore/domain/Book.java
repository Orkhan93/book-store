package az.spring.bookstore.domain;

import az.spring.bookstore.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "Book.getAllBooks",
        query = "select new az.spring.bookstore.wrapper.BookWrapper(b.id,b.name,b.status,b.authorId) " +
                "from Book b where b.status='TRUE'")

@Entity
@Setter
@Getter
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Column(name = "author")
    private Long authorId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

}