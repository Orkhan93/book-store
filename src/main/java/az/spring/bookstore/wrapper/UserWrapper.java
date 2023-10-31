package az.spring.bookstore.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserWrapper {

    private Long id;
    private String name;
    private String email;
    private String username;
    private Integer age;

}