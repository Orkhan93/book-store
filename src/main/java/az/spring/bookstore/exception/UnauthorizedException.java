package az.spring.bookstore.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String code, String message) {
        super(message);
    }

}