package com.drmed.base.visit.dto;

import com.drmed.api.apimedic.diagnosis.dto.DiagnosisDto;
import com.drmed.api.apimedic.symptoms.dto.SymptomDto;
import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.base.patient.dto.PatientInfoDto;
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
public class VisitDto {
    private Long id;
    private String code;
    private LocalDate dateOfVisit;
    private PatientInfoDto patient;
    private DoctorInfoDto doctor;
    private List<SymptomDto> symptomList;
    private List<OrderInfoDto> orderList;
    private DiagnosisDto diagnosisDto;
}
