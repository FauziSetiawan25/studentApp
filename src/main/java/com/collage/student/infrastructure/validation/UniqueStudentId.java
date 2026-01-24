package com.collage.student.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueStudentIdValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueStudentId {

    String message() default "Student ID already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
