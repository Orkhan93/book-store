package az.spring.bookstore.exception.error;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

    private String code;
    private String message;

}