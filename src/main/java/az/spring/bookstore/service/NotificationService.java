package az.spring.bookstore.service;

import az.spring.bookstore.wrapper.StudentWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final AuthorService authorService;
    private final EmailService emailService;

    public void sendNotificationToAllStudents(String subject, String message, Long authorId) {
        List<StudentWrapper> students = authorService.getStudentsByAuthorId(authorId);
        log.info("Inside students {}", students);
        emailService.sendEmailToStudents(students, subject, message);
    }

}