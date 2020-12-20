package com.drmed.base.doctor.domain;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.visit.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
    private ActivityStatus doctorStatus;
    private String email;

    private List<Long> visitIdList = new ArrayList<>();
    private List<Visit> visitList = new ArrayList<>();

    public Doctor(Long id, String code, String firstName, String lastName, ActivityStatus doctorStatus,
                  String email, List<Long> visitIdList) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorStatus = doctorStatus;
        this.email = email;
        this.visitIdList = visitIdList;
    }

    public static class DoctorBuilder {
        private Long id;
        private String code;
        private String firstName;
        private String lastName;
        private ActivityStatus doctorStatus;
        private String email;
        private List<Long> visitIdList = new ArrayList<>();
        private List<Visit> visitList = new ArrayList<>();

        public DoctorBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public DoctorBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public DoctorBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public DoctorBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public DoctorBuilder setDoctorStatus(ActivityStatus doctorStatus) {
            this.doctorStatus = doctorStatus;
            return this;
        }

        public DoctorBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public DoctorBuilder setVisitIdList(List<Long> visitIdList) {
            this.visitIdList = visitIdList;
            return this;
        }

        public DoctorBuilder setVisitList(List<Visit> visitList) {
            this.visitList = visitList;
            return this;
        }

        public Doctor build() {
            return new Doctor(id, code, firstName, lastName, doctorStatus, email, visitIdList, visitList);
        }
    }
}
