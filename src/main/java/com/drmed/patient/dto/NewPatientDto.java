package com.drmed.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class NewPatientDto {
    private String MRN;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long doctorId;
}
