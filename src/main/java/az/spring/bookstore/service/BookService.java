package az.spring.bookstore.service;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Book;
import az.spring.bookstore.enums.BookStatus;
import az.spring.bookstore.enums.Role;
import az.spring.bookstore.exception.AuthorNotFoundException;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.exception.error.ErrorMessage;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.AuthorRepository;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.request.BookRequest;
import az.spring.bookstore.response.BookResponse;
import az.spring.bookstore.wrapper.BookWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    public ResponseEntity<BookResponse> createBook(BookRequest bookRequest, Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (Objects.nonNull(author) && author.getRole().equals(Role.AUTHOR)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(bookMapper.fromModelToResponse
                            (bookRepository.save(bookMapper.fromRequestToModel(bookRequest))));
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<BookResponse> updateBook(BookRequest bookRequest, Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (Objects.nonNull(author) && author.getRole().equals(Role.AUTHOR)) {
            Book book = bookRepository.findById(bookRequest.getId()).orElseThrow(
                    () -> new BookNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BOOK_NOT_FOUND));
            if (Objects.nonNull(book)) {
                Book updatedBook = bookMapper.fromRequestToModel(bookRequest);
                updatedBook.setStatus(BookStatus.TRUE);
                updatedBook.setAuthor(author);
                return ResponseEntity.status(HttpStatus.OK).body(bookMapper.fromModelToResponse(updatedBook));
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<BookResponse> getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new BookNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BOOK_NOT_FOUND));
        if (Objects.nonNull(book)) {
            return ResponseEntity.status(HttpStatus.OK).body(bookMapper.fromModelToResponse(book));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<List<BookWrapper>> getAllBooksByStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.getAllBooks());
    }

    public void deleteBookById(Long bookId, Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (Objects.nonNull(author) && author.getRole().equals(Role.AUTHOR)) {
            Book book = bookRepository.findById(bookId).orElseThrow(
                    () -> new BookNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BOOK_NOT_FOUND));
            bookRepository.delete(book);
        }
    }

}