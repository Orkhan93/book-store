package az.spring.bookstore.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentWrapper {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Long authorId;

}