package az.spring.bookstore.controller;

import az.spring.bookstore.request.ChangePasswordRequest;
import az.spring.bookstore.request.LoginRequest;
import az.spring.bookstore.request.StudentRequest;
import az.spring.bookstore.response.StudentResponse;
import az.spring.bookstore.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/signup")
    public ResponseEntity<?> studentLogin(@RequestBody StudentRequest studentRequest) {
        return studentService.signUp(studentRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return studentService.login(loginRequest);
    }

    @PutMapping("/changePassword/{studentId}")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                               @PathVariable(name = "studentId") Long studentId) {
        studentService.changePassword(changePasswordRequest, studentId);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(studentRequest);
    }

}