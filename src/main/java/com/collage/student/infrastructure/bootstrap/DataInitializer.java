package com.collage.student.infrastructure.bootstrap;

import com.collage.student.application.service.StudentService;
import com.collage.student.domain.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initStudents(StudentService studentService) {
        return args -> {

            studentService.create(new Student(
                    "S001",
                    "Budi",
                    "Santoso",
                    LocalDate.of(2000, 3, 15)
            ));

            studentService.create(new Student(
                    "S002",
                    "Ani",
                    null,
                    LocalDate.of(1999, 7, 20)
            ));

            studentService.create(new Student(
                    "S003",
                    "Rina",
                    "Wijaya",
                    LocalDate.of(2001, 1, 5)
            ));

        };
    }
}
