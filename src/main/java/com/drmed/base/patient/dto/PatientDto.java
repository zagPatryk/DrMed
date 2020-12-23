package com.drmed.base.patient.dto;

import com.drmed.base.additional.Gender;
import com.drmed.base.visit.dto.VisitInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PatientDto {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private List<VisitInfoDto> visitInfoDtoList;
}
