package com.collage.student.presentation.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record StudentRequest(

        @NotBlank(message = "ID wajib diisi")
        String id,

        @NotBlank(message = "Nama depan wajib diisi")
        @Pattern(
                regexp = "^[A-Za-z]+$",
                message = "Nama depan hanya boleh berisi huruf alfabet"
        )
        String namaDepan,

        @Pattern(
                regexp = "^[A-Za-z]*$",
                message = "Nama belakang hanya boleh berisi huruf alfabet"
        )
        String namaBelakang,

        @PastOrPresent(message = "Tanggal lahir tidak boleh melebihi hari ini")
        LocalDate tanggalLahir
) {}