package com.drmed.base.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatientInfoDto {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
}
