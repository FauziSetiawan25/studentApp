package com.collage.student.application.service;

import com.collage.student.domain.model.Student;
import com.collage.student.infrastructure.persistence.StudentEntity;
import com.collage.student.infrastructure.persistence.StudentRepository;
import com.collage.student.presentation.controller.StudentController;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // converse data from entity to domain
    public Student toDomain(StudentEntity studentEntity) {
        return new Student(studentEntity.getId(), studentEntity.getFirstName(), studentEntity.getLastName(), studentEntity.getBirthDate());
    }

    // get all students data from db
    public List<Student> getAllStudents() {
        return studentRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    // insert student data to db
    public Student create(Student student) {
        StudentEntity entity = new StudentEntity(student.getId(), student.getFirstName(), student.getLastName(), student.getBirthDate());

        StudentEntity saved = studentRepository.save(entity);
        System.out.println("STUDENT ID = " + student.getId());
        return toDomain(saved);
    }

    // get one student data using id
    public Student getById(String id) {
        return studentRepository.findById(id)
                .map(this::toDomain)
                .orElseThrow(() -> new StudentController.StudentNotFoundException(id));
    }

    // update student data
    public Student update(String id, Student updated) {
        StudentEntity entity = studentRepository.findById(id)
                .orElseThrow(() -> new StudentController.StudentNotFoundException(id));

        entity.setFirstName(updated.getFirstName());
        entity.setLastName(updated.getLastName());
        entity.setBirthDate(updated.getBirthDate());

        return toDomain(studentRepository.save(entity));
    }

    // delete student data
    public void delete(String id) {
        studentRepository.deleteById(id);
    }

}
