package az.spring.bookstore.controller;

import az.spring.bookstore.request.NotificationRequest;
import az.spring.bookstore.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send-notification/{authorId}")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest notificationRequest,
                                                   @PathVariable(name = "authorId") Long authorId) {
        notificationService.sendNotificationToAllStudents(notificationRequest.getSubject(),
                notificationRequest.getMessage(), authorId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}