package az.spring.bookstore.controller;

import az.spring.bookstore.request.AuthorRequest;
import az.spring.bookstore.request.ChangePasswordRequest;
import az.spring.bookstore.request.LoginRequest;
import az.spring.bookstore.response.AuthorResponse;
import az.spring.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/signup")
    public ResponseEntity<?> authorSignup(@RequestBody AuthorRequest authorRequest) {
        return authorService.signUp(authorRequest);
    }

    @PostMapping("/login")
    public String authorLogin(@RequestBody LoginRequest loginRequest) {
        return authorService.login(loginRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthorResponse> updateAuthor(@RequestBody AuthorRequest authorRequest) {
        return authorService.updateAuthor(authorRequest);
    }

    @PutMapping("/changePassword/{authorId}")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                               @PathVariable(name = "authorId") Long authorId) {
        authorService.changePassword(changePasswordRequest, authorId);
    }

}