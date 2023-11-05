package az.spring.bookstore.domain;

import az.spring.bookstore.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "author_id")
    private Long authorId;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Author> authors;

    @ManyToOne
    @JoinTable(name = "student_author", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonIgnore
    private Author author;

}