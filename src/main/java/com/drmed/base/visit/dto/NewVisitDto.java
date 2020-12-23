package com.drmed.base.visit.dto;

import com.drmed.api.apimedic.symptoms.dto.SymptomDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewVisitDto {
    private String code;
    private LocalDate dateOfVisit;
    private Long patientId;
    private Long doctorId;
    private List<SymptomDto> symptomList;

    public NewVisitDto(String code, LocalDate dateOfVisit, Long patientId, Long doctorId) {
        this.code = code;
        this.dateOfVisit = dateOfVisit;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public NewVisitDto(LocalDate dateOfVisit, Long patientId, Long doctorId) {
        this.dateOfVisit = dateOfVisit;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }
}
