//package service;
//
//import az.spring.bookstore.domain.Book;
//import az.spring.bookstore.domain.Student;
//import az.spring.bookstore.exception.AuthorNotFoundException;
//import az.spring.bookstore.mapper.BookMapper;
//import az.spring.bookstore.mapper.BookMapperImpl;
//import az.spring.bookstore.repository.AuthorRepository;
//import az.spring.bookstore.repository.BookRepository;
//import az.spring.bookstore.response.BookResponse;
//import az.spring.bookstore.service.BookService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import util.Util;
//
//import java.util.Optional;
//
//import static az.spring.bookstore.enums.BookStatus.TRUE;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @Mock
//    private AuthorRepository authorRepository;
//
//    @Spy
//    private BookMapper bookMapper = new BookMapperImpl();
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Test
//    public void testBookSave_whenCreateBook_shouldReturnBookResponse() {
//        when(authorRepository.findById(1L)).thenReturn(Optional.of(Util.author()));
//        when(authorRepository.findById(0L)).thenThrow(AuthorNotFoundException.class);
//
//        Book book = new Book();
//        book.setId(Util.book().getId());
//        book.setStudent(Util.student());
//        book.setAuthor(Util.author());
//        book.setStatus(TRUE);
//        book.setName(Util.book().getName());
//
//        when(bookRepository.save(book)).thenReturn(book);
//
//        ResponseEntity<BookResponse> response = bookService.createBook(Util.bookRequest(), Util.author().getId());
//        assertThat(response).isNotNull();
//        assertEquals(book.getId(), 1L);
//
//        verify(bookRepository).save(book);
//        verifyNoMoreInteractions(bookRepository);
//    }
//
//}