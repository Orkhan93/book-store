package az.spring.bookstore.repository;

import az.spring.bookstore.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmailEqualsIgnoreCase(String email);

}