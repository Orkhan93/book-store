package az.spring.bookstore.domain;

import az.spring.bookstore.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@NamedQuery(name = "Author.getAllStudentsByAuthorId",
        query = "select new az.spring.bookstore.wrapper.StudentWrapper(s.id,s.name,s.email,s.age,s.authorId)" +
                " from Student s where s.author.id=:authorId")

@Entity
@Setter
@Getter
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "student_id")
    private Long studentId;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Student> students;

    @ManyToOne
    @JoinTable(name = "student_author", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Student student;

}