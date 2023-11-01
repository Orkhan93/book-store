package az.spring.bookstore.repository;

import az.spring.bookstore.domain.Book;
import az.spring.bookstore.wrapper.BookWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<BookWrapper> getAllBooks();

}