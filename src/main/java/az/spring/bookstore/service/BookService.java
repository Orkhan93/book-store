package az.spring.bookstore.service;

import az.spring.bookstore.constans.BookStore;
import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Book;
import az.spring.bookstore.domain.Student;
import az.spring.bookstore.enums.BookStatus;
import az.spring.bookstore.enums.Role;
import az.spring.bookstore.exception.AuthorNotFoundException;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.exception.StudentNotFoundException;
import az.spring.bookstore.exception.error.ErrorMessage;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.repository.AuthorRepository;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.repository.StudentRepository;
import az.spring.bookstore.repository.SubscriptionRepository;
import az.spring.bookstore.request.BookRequest;
import az.spring.bookstore.response.BookResponse;
import az.spring.bookstore.wrapper.BookWrapper;
import az.spring.bookstore.wrapper.StudentWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final StudentRepository studentRepository;
    private final AuthorRepository authorRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final AuthorService authorService;
    private final EmailService emailService;

    public ResponseEntity<BookResponse> createBook(BookRequest bookRequest, Long authorId) {
        log.info("Inside bookRequest {}", bookRequest);
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (Objects.nonNull(author) && author.getRole().equals(Role.AUTHOR)) {
            List<StudentWrapper> students = authorRepository.getAllStudentsByAuthorId(authorId);
            Book book = bookMapper.fromRequestToModel(bookRequest);
            book.setStatus(BookStatus.TRUE);
            book.setAuthor(author);
            emailService.sendEmailToStudents(students, BookStore.EMAIL_SUBJECT, BookStore.EMAIL_MESSAGE);
            log.info("Inside createBook {}", book);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(bookMapper.fromModelToResponse(bookRepository.save(book)));
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<BookResponse> updateBook(BookRequest bookRequest, Long authorId) {
        log.info("Inside bookRequest {}", bookRequest);
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (Objects.nonNull(author) && author.getRole().equals(Role.AUTHOR)) {
            Book book = bookRepository.findById(bookRequest.getId()).orElseThrow(
                    () -> new BookNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BOOK_NOT_FOUND));
            if (Objects.nonNull(book)) {
                Book updatedBook = bookMapper.fromRequestToModel(bookRequest);
                updatedBook.setAuthor(author);
                log.info("Inside updatedBook {}", updatedBook);
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
            log.info("Inside getBookById {}", book);
            return ResponseEntity.status(HttpStatus.OK).body(bookMapper.fromModelToResponse(book));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<List<BookWrapper>> getAllBooksByStatus() {
        log.info("Inside getAllBooksByStatus {}", bookRepository.getAllBooks());
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.getAllBooks());
    }

    public ResponseEntity<BookResponse> addSpecificBookByStudent(Long studentId, Long bookId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(student)) {
            log.info("Inside student {}", student);
            Book book = bookRepository.findById(bookId).orElseThrow(
                    () -> new BookNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BOOK_NOT_FOUND));
            if (book.getStatus().equals(BookStatus.TRUE) && Objects.isNull(book.getStudent())) {
                book.setStudent(student);
                book.setStatus(BookStatus.FALSE);
                log.info("Inside addSpecificBookByStudent {}", book);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(bookMapper.fromModelToResponse(bookRepository.save(book)));
            }
            return ResponseEntity.status(BAD_REQUEST).build();
        }
        return ResponseEntity.status(BAD_REQUEST).build();
    }

    public ResponseEntity<BookResponse> returnBookByStudent(Long studentId, Long bookId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.USER_NOT_FOUND));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BOOK_NOT_FOUND));
        if (student.getId().equals(book.getStudent().getId()) && book.getStatus().equals(BookStatus.FALSE)) {
            book.setStudent(null);
            book.setStatus(BookStatus.TRUE);
            log.info("Inside returnBookByStudent {}", book);
            bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookMapper.fromModelToResponse(book));
        }
        return ResponseEntity.status(BAD_REQUEST).build();
    }

    public void deleteBookById(Long bookId, Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (Objects.nonNull(author) && author.getRole().equals(Role.AUTHOR)) {
            Book book = bookRepository.findById(bookId).orElseThrow(
                    () -> new BookNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BOOK_NOT_FOUND));
            log.info("Inside deleteBookById {}", book);
            bookRepository.delete(book);
        }
    }

}