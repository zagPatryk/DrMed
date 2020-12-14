package com.drmed.base.visit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewVisitDto {
    private String code;
    private LocalDate dateOfVisit;
    private Long patientId;
    private Long doctorId;
}
