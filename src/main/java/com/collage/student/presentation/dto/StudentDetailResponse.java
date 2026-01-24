package com.collage.student.presentation.dto;

import java.time.LocalDate;

public record StudentDetailResponse(String id, String firstName, String lastName, LocalDate birthDate) {}
