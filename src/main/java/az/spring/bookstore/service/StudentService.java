package az.spring.bookstore.service;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Student;
import az.spring.bookstore.enums.Role;
import az.spring.bookstore.exception.AuthorNotFoundException;
import az.spring.bookstore.exception.IncorrectPasswordException;
import az.spring.bookstore.exception.StudentNotFoundException;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.exception.error.ErrorMessage;
import az.spring.bookstore.mapper.StudentMapper;
import az.spring.bookstore.repository.StudentRepository;
import az.spring.bookstore.request.AuthorRequest;
import az.spring.bookstore.request.ChangePasswordRequest;
import az.spring.bookstore.request.LoginRequest;
import az.spring.bookstore.request.StudentRequest;
import az.spring.bookstore.response.AuthorResponse;
import az.spring.bookstore.response.StudentResponse;
import az.spring.bookstore.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static az.spring.bookstore.constans.BookStore.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final JwtUtil jwtUtil;
    private final EncryptionService encryptionService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> signUp(StudentRequest studentRequest) {
        log.info("Inside studentRequest {}", studentRequest);
        if (validationSignUp(studentRequest)) {
            Student student = studentRepository.findByEmailEqualsIgnoreCase(studentRequest.getEmail());
            if (Objects.isNull(student)) {
                Student saved = studentMapper.fromRequestToModel(studentRequest);
                saved.setPassword(encryptionService.encryptPassword(studentRequest.getPassword()));
                saved.setRole(Role.STUDENT);
                log.info("Inside signUp student{}", saved);
                return ResponseEntity.status(CREATED)
                        .body(studentRepository.save(saved));
            } else {
                log.error("studentRequest {}", studentRequest);
                return ResponseEntity.status(BAD_REQUEST).body(USER_ALREADY_EXISTS);
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public String login(LoginRequest loginRequest) {
        log.info("Inside loginRequest student{}", loginRequest);
        Student student = studentRepository.findByEmailEqualsIgnoreCase(loginRequest.getEmail());
        if (Objects.nonNull(student)) {
            if (encryptionService.verifyPassword(loginRequest.getPassword(), student.getPassword())) {
                return jwtUtil.generateTokenTest(loginRequest.getEmail());
            } else
                return INVALID_DATA;
        }
        log.error("login student {}", student);
        return BAD_CREDENTIALS;
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest, Long studentId) {
        log.info("Inside changePasswordRequest student{}", changePasswordRequest);
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.STUDENT_NOT_FOUND));
        if (!encryptionService.verifyPassword(changePasswordRequest.getOldPassword(), student.getPassword())) {
            throw new IncorrectPasswordException(BAD_REQUEST.name(), ErrorMessage.INCORRECT_PASSWORD);
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new IncorrectPasswordException(BAD_REQUEST.name(), ErrorMessage.NOT_MATCHES);
        }
        student.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        studentRepository.save(student);
        log.info("changePassword student{}", student);
    }

    public ResponseEntity<StudentResponse> updateStudent(StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentRequest.getId()).orElseThrow(
                () -> new StudentNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.STUDENT_NOT_FOUND));
        if (Objects.nonNull(student)) {
            Student updatedStudent = studentMapper.fromRequestToModel(studentRequest);
            updatedStudent.setPassword(encryptionService.encryptPassword(studentRequest.getPassword()));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(studentMapper.fromModelToResponse(studentRepository.save(updatedStudent)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private boolean validationSignUp(StudentRequest studentRequest) {
        return studentRequest.getName() != null && studentRequest.getEmail() != null
                && studentRequest.getPassword() != null;
    }

}