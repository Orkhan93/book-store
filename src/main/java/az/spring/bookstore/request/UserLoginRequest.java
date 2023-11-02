package az.spring.bookstore.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {

    private String email;
    private String password;

}