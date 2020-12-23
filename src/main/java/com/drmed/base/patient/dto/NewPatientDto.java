package com.drmed.base.patient.dto;

import com.drmed.base.additional.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPatientDto {
    private String code;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
}
