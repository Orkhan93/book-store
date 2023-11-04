package az.spring.bookstore.service;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Student;
import az.spring.bookstore.domain.Subscription;
import az.spring.bookstore.exception.AuthorNotFoundException;
import az.spring.bookstore.exception.StudentNotFoundException;
import az.spring.bookstore.exception.error.ErrorMessage;
import az.spring.bookstore.mapper.SubscriptionMapper;
import az.spring.bookstore.repository.AuthorRepository;
import az.spring.bookstore.repository.StudentRepository;
import az.spring.bookstore.repository.SubscriptionRepository;
import az.spring.bookstore.response.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final StudentRepository studentRepository;
    private final AuthorRepository authorRepository;

    public ResponseEntity<SubscriptionResponse> subscribeStudentToAuthor(Long studentId, Long authorId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.STUDENT_NOT_FOUND));
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new AuthorNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (Objects.nonNull(student) && Objects.nonNull(author)) {
            Subscription subscription = new Subscription();
            subscription.setStudent(student);
            subscription.setAuthor(author);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(subscriptionMapper.fromModelToResponse(subscriptionRepository.save(subscription)));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}