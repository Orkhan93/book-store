package az.spring.bookstore.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String code, String message) {
        super(message);
    }

}