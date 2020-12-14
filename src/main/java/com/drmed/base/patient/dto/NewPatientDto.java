package com.drmed.base.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class NewPatientDto {
    private String code;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
