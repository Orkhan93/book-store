package az.spring.bookstore.controller;

import az.spring.bookstore.request.BookRequest;
import az.spring.bookstore.response.BookResponse;
import az.spring.bookstore.service.BookService;
import az.spring.bookstore.wrapper.BookWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add/{authorId}")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest,
                                                   @PathVariable(name = "authorId") Long authorId) {
        return bookService.createBook(bookRequest, authorId);
    }

    @PutMapping("/update/{authorId}")
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookRequest bookRequest,
                                                   @PathVariable(name = "authorId") Long authorId) {
        return bookService.updateBook(bookRequest, authorId);
    }

    @GetMapping("/get/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable(name = "bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookWrapper>> getAllBooksByStatus() {
        return bookService.getAllBooksByStatus();
    }

    @DeleteMapping("/{bookId}/delete/{authorId}")
    public void deleteBookById(@PathVariable(name = "bookId") Long bookId,
                               @PathVariable(name = "authorId") Long authorId) {
        bookService.deleteBookById(bookId, authorId);
    }

}