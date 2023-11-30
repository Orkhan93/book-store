package az.spring.bookstore;

import az.spring.bookstore.constans.BookStore;
import az.spring.bookstore.domain.Book;
import az.spring.bookstore.domain.Student;
import az.spring.bookstore.exception.AuthorNotFoundException;
import az.spring.bookstore.exception.BookNotFoundException;
import az.spring.bookstore.mapper.BookMapper;
import az.spring.bookstore.mapper.BookMapperImpl;
import az.spring.bookstore.repository.AuthorRepository;
import az.spring.bookstore.repository.BookRepository;
import az.spring.bookstore.repository.StudentRepository;
import az.spring.bookstore.response.BookResponse;
import az.spring.bookstore.service.BookService;
import az.spring.bookstore.service.EmailService;
import az.spring.bookstore.util.Util;
import az.spring.bookstore.wrapper.BookWrapper;
import az.spring.bookstore.wrapper.StudentWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private EmailService emailService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Spy
    private BookMapper bookMapper = new BookMapperImpl();

    @BeforeEach
    void setUp() {
        Optional<Book> book = Optional.of(Util.book());
        when(bookRepository.findById(1L)).thenReturn(book);
    }

    @Test
    public void testGetBookById_whenGetBookById_shouldReturnBookResponse() {
        when(bookRepository.findById(Util.book().getId())).thenReturn(Optional.of(Util.book()));
        when(bookRepository.findById(0L)).thenThrow(BookNotFoundException.class);
        String name = "new-Book";
        BookResponse bookResponse = bookService.getBookById(1L);
        assertEquals(name, bookResponse.getName());
        assertThat(bookResponse).isNotNull();

        verify(bookRepository).findById(Util.book().getId());
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testGetAllBooks_whenGetAllBooks_shouldReturnAllBooksByStatus() {
        List<Book> list = new ArrayList<>();
        list.add(Util.book());
        list.add(Util.book2());
        list.add(Util.book3());

        when(bookRepository.findAll()).thenReturn(list);
        assertThat(list).isNotNull();
        assertTrue(list.size() > 1);

        List<BookWrapper> allBooks = bookRepository.getAllBooks();
        allBooks.add(Util.bookWrapper());
        allBooks.add(Util.bookWrapper2());
        allBooks.add(Util.bookWrapper3());

        verify(bookRepository).getAllBooks();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testAddBook_whenCreateNewBook_shouldReturnBookResponse() {
        List<Student> list = new ArrayList<>();
        list.add(Util.student());
        list.add(Util.student2());

        List<StudentWrapper> wrapperList = new ArrayList<>();
        wrapperList.add(Util.studentWrapper());
        wrapperList.add(Util.studentWrapper2());

        when(authorRepository.findById(Util.author().getId())).thenReturn(Optional.of(Util.author()));
        when(authorRepository.findById(0L)).thenThrow(AuthorNotFoundException.class);
        when(bookRepository.save(Util.book())).thenReturn(Util.book());
        when(studentRepository.findAll()).thenReturn(list);
        emailService.sendEmailToStudents(wrapperList, BookStore.EMAIL_SUBJECT, BookStore.EMAIL_MESSAGE);
        String name = "new-Book";
        Long authorId = 1L;

        BookResponse bookResponse = bookService.createBook(Util.bookRequest(), Util.author().getId());
        assertThat(bookResponse).isNotNull();
    }

    @Test
    public void testDeleteBookById() {
        bookRepository.deleteById(2L);
        verify(bookRepository, Mockito.times(1)).deleteById(2L);
        verifyNoMoreInteractions(bookRepository);
    }

}