package com.drmed.base.patient.domain;

import com.drmed.base.visit.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    private List<Long> visitIdList = new ArrayList<>();
    private List<Visit> visitList = new ArrayList<>();

    public Patient(Long id, String code, String firstName, String lastName, LocalDate birthDate, List<Long> visitIdList) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.visitIdList = visitIdList;
    }

    public Patient(String code, String firstName, String lastName, LocalDate birthDate) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
