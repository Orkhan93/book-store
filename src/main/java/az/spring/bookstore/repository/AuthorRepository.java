package az.spring.bookstore.repository;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.wrapper.StudentWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByEmailEqualsIgnoreCase(String email);

    List<StudentWrapper> getAllStudentsByAuthorId(Long authorId);


}