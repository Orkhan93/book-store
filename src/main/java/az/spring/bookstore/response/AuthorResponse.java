package az.spring.bookstore.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorResponse {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String password;
    private String role;

}