package az.spring.bookstore.controller;

import az.spring.bookstore.domain.Subscription;
import az.spring.bookstore.response.SubscriptionResponse;
import az.spring.bookstore.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/{studentId}/subscribe/{authorId}")
    public ResponseEntity<SubscriptionResponse> subscribeStudentToAuthor(@PathVariable(name = "studentId") Long studentId,
                                                                         @PathVariable(name = "authorId") Long authorId) {
        return subscriptionService.subscribeStudentToAuthor(studentId, authorId);
    }

}