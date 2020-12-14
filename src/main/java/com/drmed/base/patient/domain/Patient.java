package com.drmed.base.patient.domain;

import com.drmed.base.doctor.domain.Doctor;
import com.drmed.base.order.domain.Order;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    private Long id;
    private String MRN;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    private Long doctorId;
    private Doctor doctor;

    private List<Long> ordersIds;
    private List<Order> orders;

    public Patient(Long id, String MRN, String firstName, String lastName, LocalDate birthDate, Long doctorId, List<Long> ordersIds) {
        this.id = id;
        this.MRN = MRN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.doctorId = doctorId;
        this.ordersIds = ordersIds;
    }

    public Patient(String MRN, String firstName, String lastName, LocalDate birthDate, Long doctorId) {
        this.MRN = MRN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.doctorId = doctorId;
    }
}
