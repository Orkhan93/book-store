package az.spring.bookstore.request;

import az.spring.bookstore.constraint.validation.Password;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {

    @Email
    private String email;

    @Password
    private String password;

}