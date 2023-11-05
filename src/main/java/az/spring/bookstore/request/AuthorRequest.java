package az.spring.bookstore.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorRequest {

    private Long id;
    private String name;
    private String authorName;
    private Integer age;
    private String email;
    private String password;
    private String role;
    private Long studentId;

}