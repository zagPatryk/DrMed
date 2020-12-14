package com.drmed.base.visit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VisitInfoDto {
    private Long id;
    private String code;
    private LocalDate dateOfVisit;
}
