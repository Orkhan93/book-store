package util;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Book;
import az.spring.bookstore.domain.Student;
import az.spring.bookstore.enums.Role;
import az.spring.bookstore.request.BookRequest;
import az.spring.bookstore.request.StudentRequest;
import az.spring.bookstore.response.BookResponse;
import az.spring.bookstore.response.StudentResponse;

public class Util {

    private Util() {
    }

    public static Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setPassword("123456");
        student.setName("student");
        student.setAge(10);
        student.setEmail("email@mail.com");
        student.setRole(Role.STUDENT);
        return student;
    }

    public static StudentRequest request() {
        StudentRequest request = new StudentRequest();
        request.setId(1L);
        request.setPassword("123456");
        request.setName("student");
        request.setAge(10);
        request.setEmail("email@mail.com");
        request.setRole(Role.STUDENT.name());
        return request;
    }

    public static StudentResponse response() {
        StudentResponse response = new StudentResponse();
        response.setId(1L);
        response.setPassword("123456");
        response.setName("student");
        response.setAge(10);
        response.setEmail("email@mail.com");
        response.setRole(Role.STUDENT.name());
        return response;
    }

    public static Author author() {
        Author author = new Author();
        author.setId(1L);
        author.setPassword("123456");
        author.setAuthorName("author-name");
        author.setAge(20);
        author.setEmail("test@mail.com");
        author.setRole(Role.AUTHOR);
        return author;
    }

    public static Book book() {
        Book book = new Book();
        book.setId(1L);
        book.setName("Book-name");
        book.setAuthor(Util.author());
        book.setStudent(Util.student());
        return book;
    }

    public static BookRequest bookRequest() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setId(1L);
        bookRequest.setName("Book-name");
        bookRequest.setAuthorId(Util.author().getId());
        bookRequest.setStudentId(Util.student().getId());
        return bookRequest;
    }

    public static BookResponse bookResponse() {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(1L);
        bookResponse.setName("Book-name");
        bookResponse.setAuthorId(Util.author().getId());
        return bookResponse;
    }

}