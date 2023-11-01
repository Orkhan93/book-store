package az.spring.bookstore.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

}