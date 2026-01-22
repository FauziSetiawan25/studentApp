package com.collage.student.presentation.controller;

import com.collage.student.domain.model.Student;

public record StudentResponse(String ID, String fullName, int age) {
    public static StudentResponse from(Student student) {
        return new StudentResponse(student.getId(), student.getNamaLengkap(), student.getUsia());
    }
}
