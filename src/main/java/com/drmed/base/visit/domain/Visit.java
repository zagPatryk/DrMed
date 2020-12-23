package com.drmed.base.visit.domain;

import com.drmed.api.apimedic.symptoms.domain.Symptom;
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
    private List<Symptom> symptomList;
    private List<Long> orderIdsList;
    private List<Order> orderList;

    public Visit(Long id, String code, LocalDate dateOfVisit, Patient patient, Doctor doctor, List<Symptom> symptomList,
                 List<Long> orderIdsList) {
        this.id = id;
        this.code = code;
        this.dateOfVisit = dateOfVisit;
        this.patient = patient;
        this.doctor = doctor;
        this.symptomList = symptomList;
        this.orderIdsList = orderIdsList;
    }

    public static class VisitBuilder {
        private Long id;
        private String code;
        private LocalDate dateOfVisit;
        private Patient patient;
        private Doctor doctor;
        private List<Symptom> symptomList;
        private List<Long> orderIdsList;
        private List<Order> orderList;

        public VisitBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public VisitBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public VisitBuilder setDateOfVisit(LocalDate dateOfVisit) {
            this.dateOfVisit = dateOfVisit;
            return this;
        }

        public VisitBuilder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public VisitBuilder setDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public VisitBuilder setOrderIdsList(List<Long> orderIdsList) {
            this.orderIdsList = orderIdsList;
            return this;
        }

        public VisitBuilder setOrderList(List<Order> orderList) {
            this.orderList = orderList;
            return this;
        }

        public VisitBuilder setSymptomList(List<Symptom> symptomList) {
            this.symptomList = symptomList;
            return this;
        }

        public Visit build() {
            return new Visit(id, code, dateOfVisit, patient, doctor, symptomList, orderIdsList, orderList);
        }
    }
}
