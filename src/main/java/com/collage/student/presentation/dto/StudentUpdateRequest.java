package com.collage.student.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record StudentUpdateRequest(

        @NotBlank(message = "The first name is required")
        @Pattern(
                regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$",
                message = "The first name must only contain letters of the alphabet"
        )
        String firstName,

        @Pattern(
                regexp = "^[A-Za-z]*([ '-][A-Za-z]+)*$",
                message = "The last name must only contain letters of the alphabet"
        )
        String lastName,

        @NotNull(message = "Birth date is required")
        @PastOrPresent(message = "The birth date cannot exceed today")
        LocalDate birthDate
) {}

