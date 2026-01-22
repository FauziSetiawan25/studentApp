package com.collage.student.domain.model;

import java.time.LocalDate;
import java.time.Period;

public class Student {
    private final String id;
    private final String namaDepan;
    private final String namaBelakang;
    private final LocalDate tanggalLahir;


    public Student(String id,String namaDepan,String namaBelakang,LocalDate tanggalLahir) {
        this.id = id;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.tanggalLahir = tanggalLahir;
    }

    public String getId() {
        return id;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public String getNamaLengkap() {
        if (namaBelakang==null||namaBelakang.isBlank()) {
            return namaDepan;
        }else  {
            return namaDepan+ " " +namaBelakang;
        }
    }

    public int getUsia() {
        return Period.between(tanggalLahir,LocalDate.now()).getYears();
    }
}
