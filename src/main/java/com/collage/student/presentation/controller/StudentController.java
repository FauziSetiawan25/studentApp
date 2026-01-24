package com.collage.student.presentation.controller;

import com.collage.student.application.service.StudentService;
import com.collage.student.domain.model.Student;
import com.collage.student.presentation.dto.StudentDetailResponse;
import com.collage.student.presentation.dto.StudentCreateRequest;
import com.collage.student.presentation.dto.StudentResponse;
import com.collage.student.presentation.dto.StudentUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents()
                .stream()
                .map(StudentResponse::from)
                .toList();
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentCreateRequest request) {
        Student student = new Student(request.id(), request.firstName(), request.lastName(), request.birthDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentResponse.from(studentService.create(student)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDetailResponse> getById(@PathVariable String id) {
        Student student = studentService.getById(id);

        return ResponseEntity.ok(
                new StudentDetailResponse(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getBirthDate()
                )
        );
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable String id, @Valid @RequestBody StudentUpdateRequest request) {
        Student student = new Student(
                id,
                request.firstName(),
                request.lastName(),
                request.birthDate()
        );
        return StudentResponse.from(studentService.update(id,student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class StudentNotFoundException extends RuntimeException {
        public StudentNotFoundException(String id) {
            super("Student not found: " + id);
        }
    }
}
