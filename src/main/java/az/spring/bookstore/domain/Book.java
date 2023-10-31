package az.spring.bookstore.domain;

import az.spring.bookstore.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}