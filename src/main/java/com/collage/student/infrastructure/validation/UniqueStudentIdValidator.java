package com.collage.student.infrastructure.validation;

import com.collage.student.infrastructure.persistence.StudentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueStudentIdValidator
        implements ConstraintValidator<UniqueStudentId, String> {

    private final StudentRepository studentRepository;

    public UniqueStudentIdValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return !studentRepository.existsById(value);
    }
}

