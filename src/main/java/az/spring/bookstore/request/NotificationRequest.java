package az.spring.bookstore.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationRequest {

    private String subject;
    private String message;

}