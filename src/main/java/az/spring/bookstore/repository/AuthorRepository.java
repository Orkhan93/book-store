package az.spring.bookstore.repository;

import az.spring.bookstore.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByEmailEqualsIgnoreCase(String email);

}