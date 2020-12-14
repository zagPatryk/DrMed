package com.drmed.base.visit.domain;

import com.drmed.base.doctor.domain.Doctor;
import com.drmed.base.order.domain.Order;
import com.drmed.base.patient.domain.Patient;
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
public class Visit {
    private Long id;
    private String code;
    private LocalDate dateOfVisit;
    private Patient patient;
    private Doctor doctor;

    private List<Long> orderIdsList;
    private List<Order> orderList;

    public Visit(Long id, String code, LocalDate dateOfVisit, Patient patient, Doctor doctor, List<Long> orderIdsList) {
        this.id = id;
        this.code = code;
        this.dateOfVisit = dateOfVisit;
        this.patient = patient;
        this.doctor = doctor;
        this.orderIdsList = orderIdsList;
    }
}
