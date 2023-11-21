package az.spring.bookstore.service;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.enums.Role;
import az.spring.bookstore.exception.AuthorNotFoundException;
import az.spring.bookstore.exception.IncorrectPasswordException;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.exception.error.ErrorMessage;
import az.spring.bookstore.mapper.AuthorMapper;
import az.spring.bookstore.repository.AuthorRepository;
import az.spring.bookstore.repository.SubscriptionRepository;
import az.spring.bookstore.request.AuthorRequest;
import az.spring.bookstore.request.ChangePasswordRequest;
import az.spring.bookstore.request.LoginRequest;
import az.spring.bookstore.response.AuthorResponse;
import az.spring.bookstore.security.JwtUtil;
import az.spring.bookstore.wrapper.StudentWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static az.spring.bookstore.constans.BookStore.BAD_CREDENTIALS;
import static az.spring.bookstore.constans.BookStore.USER_ALREADY_EXISTS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final AuthorMapper authorMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final EncryptionService encryptionService;
    private final EmailService emailService;

    public ResponseEntity<?> signUp(AuthorRequest authorRequest) {
        log.info("Inside authorRequest {}", authorRequest);
        if (validationSignUp(authorRequest)) {
            Author author = authorRepository.findByEmailEqualsIgnoreCase(authorRequest.getEmail());
            if (Objects.isNull(author)) {
                Author saved = authorMapper.fromRequestToModel(authorRequest);
                saved.setRole(Role.AUTHOR);
                saved.setPassword(encryptionService.encryptPassword(authorRequest.getPassword()));
                log.info("Inside signUp author{}", saved);
                return ResponseEntity.status(CREATED)
                        .body(authorRepository.save(saved));
            } else {
                log.error("authorRequest {}", authorRequest);
                return ResponseEntity.status(BAD_REQUEST).body(USER_ALREADY_EXISTS);
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public String login(LoginRequest loginRequest) {
        log.info("Inside loginRequest author {}", loginRequest);
        Author author = authorRepository.findByEmailEqualsIgnoreCase(loginRequest.getEmail());
        if (Objects.nonNull(author)) {
            return jwtUtil.generateTokenTest(loginRequest.getEmail());
        }
        log.error("login author {}", author);
        return BAD_CREDENTIALS;
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest, Long authorId) {
        log.info("Inside changePasswordRequest author{}", changePasswordRequest);
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (!encryptionService.verifyPassword(changePasswordRequest.getOldPassword(), author.getPassword())) {
            throw new IncorrectPasswordException(BAD_REQUEST.name(), ErrorMessage.INCORRECT_PASSWORD);
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new IncorrectPasswordException(BAD_REQUEST.name(), ErrorMessage.NOT_MATCHES);
        }
        author.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        authorRepository.save(author);
        log.info("changePassword author{}", author);
    }

    public ResponseEntity<AuthorResponse> updateAuthor(AuthorRequest authorRequest) {
        Author author = authorRepository.findById(authorRequest.getId()).orElseThrow(
                () -> new AuthorNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.AUTHOR_NOT_FOUND));
        if (Objects.nonNull(author)) {
            Author updatedAuthor = authorMapper.fromRequestToModel(authorRequest);
            updatedAuthor.setRole(Role.AUTHOR);
            updatedAuthor.setPassword(encryptionService.encryptPassword(authorRequest.getPassword()));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(authorMapper.fromModelToResponse(authorRepository.save(updatedAuthor)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public List<StudentWrapper> getStudentsByAuthorId(Long authorId) {
        return authorRepository.getAllStudentsByAuthorId(authorId);
    }

    private boolean validationSignUp(AuthorRequest authorRequest) {
        return authorRequest.getName() != null && authorRequest.getEmail() != null && authorRequest.getPassword() != null;
    }

}