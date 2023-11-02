package az.spring.bookstore.repository;

import az.spring.bookstore.domain.Student;
import az.spring.bookstore.wrapper.BookWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmailEqualsIgnoreCase(String email);

    List<BookWrapper> getAllBooksStudentId(Long studentId);

}