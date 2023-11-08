package az.spring.bookstore.util;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Book;
import az.spring.bookstore.domain.Student;
import az.spring.bookstore.enums.BookStatus;
import az.spring.bookstore.enums.Role;
import az.spring.bookstore.request.BookRequest;
import az.spring.bookstore.response.BookResponse;
import az.spring.bookstore.wrapper.BookWrapper;
import az.spring.bookstore.wrapper.StudentWrapper;

public class Util {

    private Util() {
    }

    public static Book book() {
        Book book = new Book();
        book.setId(1L);
        book.setName("new-Book");
        book.setStatus(BookStatus.TRUE);
        book.setAuthor(author());
        book.setStudent(student());
        return book;
    }

    public static Book book2() {
        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("new-Book2");
        book2.setStatus(BookStatus.TRUE);
        book2.setAuthor(author());
        book2.setStudent(student());
        return book2;
    }

    public static Book book3() {
        Book book3 = new Book();
        book3.setId(3L);
        book3.setName("new-Book3");
        book3.setStatus(BookStatus.TRUE);
        book3.setAuthor(author());
        book3.setStudent(student());
        return book3;
    }

    public static BookResponse bookResponse() {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(1L);
        bookResponse.setName("new-Book");
        bookResponse.setAuthorId(1L);
        bookResponse.setStatus(BookStatus.TRUE);
        return bookResponse;
    }

    public static BookRequest bookRequest() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setId(1L);
        bookRequest.setName("new-Book");
        bookRequest.setAuthorId(1L);
        bookRequest.setStatus(BookStatus.TRUE);
        return bookRequest;
    }

    public static BookWrapper bookWrapper() {
        BookWrapper bookWrapper = new BookWrapper();
        bookWrapper.setId(1L);
        bookWrapper.setName("new-Book");
        bookWrapper.setStatus(BookStatus.TRUE);
        bookWrapper.setAuthorId(author().getId());
        bookWrapper.setStudentId(student().getId());
        return bookWrapper;
    }

    public static BookWrapper bookWrapper2() {
        BookWrapper bookWrapper2 = new BookWrapper();
        bookWrapper2.setId(2L);
        bookWrapper2.setName("new-Book2");
        bookWrapper2.setStatus(BookStatus.TRUE);
        bookWrapper2.setAuthorId(author().getId());
        bookWrapper2.setStudentId(student().getId());
        return bookWrapper2;
    }

    public static BookWrapper bookWrapper3() {
        BookWrapper bookWrapper3 = new BookWrapper();
        bookWrapper3.setId(3L);
        bookWrapper3.setName("new-Book3");
        bookWrapper3.setStatus(BookStatus.TRUE);
        bookWrapper3.setAuthorId(author().getId());
        bookWrapper3.setStudentId(student().getId());
        return bookWrapper3;
    }

    public static Author author() {
        Author author = new Author();
        author.setId(1L);
        author.setAuthorName("author-name");
        author.setAge(25);
        author.setRole(Role.AUTHOR);
        author.setPassword("author-password");
        author.setEmail("email-author");
        return author;
    }

    public static Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setName("student-name");
        student.setAge(25);
        student.setRole(Role.STUDENT);
        student.setPassword("password-student");
        student.setEmail("email-student");
        return student;
    }

    public static Student student2() {
        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("student2-name");
        student2.setAge(25);
        student2.setRole(Role.STUDENT);
        student2.setPassword("password-student2");
        student2.setEmail("email-student2");
        return student2;
    }

    public static StudentWrapper studentWrapper() {
        StudentWrapper studentWrapper = new StudentWrapper();
        studentWrapper.setId(2L);
        studentWrapper.setName("student2-name");
        studentWrapper.setAge(25);
        studentWrapper.setEmail("email-student2");
        return studentWrapper;
    }

    public static StudentWrapper studentWrapper2() {
        StudentWrapper studentWrapper2 = new StudentWrapper();
        studentWrapper2.setId(2L);
        studentWrapper2.setName("student2-name");
        studentWrapper2.setAge(25);
        studentWrapper2.setEmail("email-student2");
        return studentWrapper2;
    }

}