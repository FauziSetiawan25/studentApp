package com.collage.student.presentation.controller;

import java.time.LocalDate;

public record StudentRequest (String id, String namaDepan, String namaBelakang, LocalDate tanggalLahir) {}
