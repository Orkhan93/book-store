package az.spring.bookstore.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    private Long id;
    private String name;
    private String username;
    private String email;

}