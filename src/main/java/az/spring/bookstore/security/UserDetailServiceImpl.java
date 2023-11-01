package az.spring.bookstore.security;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Student;
import az.spring.bookstore.exception.AuthorNotFoundException;
import az.spring.bookstore.exception.StudentNotFoundException;
import az.spring.bookstore.exception.UserNotFoundException;
import az.spring.bookstore.exception.error.ErrorMessage;
import az.spring.bookstore.repository.AuthorRepository;
import az.spring.bookstore.repository.StudentRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AuthorRepository authorRepository;
    private final StudentRepository studentRepository;

    @Getter
    private Author authorDetail;

    @Getter
    private Student studentDetail;

    public UserDetailServiceImpl(AuthorRepository authorRepository, StudentRepository studentRepository) {
        this.authorRepository = authorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        Author author = authorRepository.findByEmailEqualsIgnoreCase(username);
        authorDetail = authorRepository.findByEmailEqualsIgnoreCase(username);
        Student student = studentRepository.findByEmailEqualsIgnoreCase(username);
        studentDetail = studentRepository.findByEmailEqualsIgnoreCase(username);
        if (Objects.nonNull(author)) {
            return new org.springframework.security.core.userdetails.User(author.getEmail(),
                    new BCryptPasswordEncoder().encode(author.getPassword()), new ArrayList<>());
        } else if (Objects.nonNull(student)) {
            return new org.springframework.security.core.userdetails.User(student.getEmail(),
                    new BCryptPasswordEncoder().encode(student.getPassword()), new ArrayList<>());
        } else {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.USER_NOT_FOUND);
        }
    }

}