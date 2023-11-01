package az.spring.bookstore.request;

import az.spring.bookstore.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String password;
    private String role;

}