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

    public Student toDomain(StudentEntity studentEntity) {
        return new Student(studentEntity.getId(), studentEntity.getNamaDepan(), studentEntity.getNamaBelakang(), studentEntity.getTanggalLahir());
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    public Student create(Student student) {
        StudentEntity entity = new StudentEntity(student.getId(), student.getNamaDepan(), student.getNamaBelakang(), student.getTanggalLahir());

        StudentEntity saved = studentRepository.save(entity);
        System.out.println("STUDENT ID = " + student.getId());
        return toDomain(saved);
    }

    public Student getById(String id) {
        return studentRepository.findById(id)
                .map(this::toDomain)
                .orElseThrow(() -> new StudentController.StudentNotFoundException(id));
    }

    public Student update(String id, Student updated) {
        StudentEntity entity = studentRepository.findById(id)
                .orElseThrow(() -> new StudentController.StudentNotFoundException(id));

        entity.setNamaDepan(updated.getNamaDepan());
        entity.setNamaBelakang(updated.getNamaBelakang());
        entity.setTanggalLahir(updated.getTanggalLahir());

        return toDomain(studentRepository.save(entity));
    }

    public void delete(String id) {
        studentRepository.deleteById(id);
    }

}
