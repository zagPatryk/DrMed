package com.drmed.domain.patient;

import com.drmed.domain.doctor.DoctorDto;
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
    private DoctorDto doctor;
    private List<Long> ordersIds;

    public PatientDto(String MRN, String firstName, String lastName, LocalDate birthDate, DoctorDto doctor, List<Long> ordersIds) {
        this.MRN = MRN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.doctor = doctor;
        this.ordersIds = ordersIds;
    }

    public PatientDto(String MRN, String firstName, String lastName, LocalDate birthDate, DoctorDto doctor) {
        this.MRN = MRN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.doctor = doctor;
    }
}
