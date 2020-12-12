package com.drmed.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatientInfoDto {
    private Long id;
    private String MRN;
    private String firstName;
    private String lastName;
}
