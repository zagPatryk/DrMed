package com.drmed.patient.dto;

import com.drmed.doctor.dto.DoctorInfoDto;
import com.drmed.order.dto.OrderInfoDto;
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
    private String MRN;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private DoctorInfoDto doctor;
    private List<OrderInfoDto> ordersList;
}
