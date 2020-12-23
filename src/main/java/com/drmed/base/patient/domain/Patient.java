package com.drmed.base.patient.domain;

import com.drmed.base.additional.Gender;
import com.drmed.base.visit.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private List<Long> visitIdList = new ArrayList<>();
    private List<Visit> visitList = new ArrayList<>();

    public Patient(Long id, String code, String firstName, String lastName, LocalDate birthDate, Gender gender, List<Long> visitIdList) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.visitIdList = visitIdList;
    }

    public Patient(String code, String firstName, String lastName, LocalDate birthDate, Gender gender) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public static class PatientBuilder {
        private Long id;
        private String code;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private Gender gender;
        private List<Long> visitIdList = new ArrayList<>();
        private List<Visit> visitList = new ArrayList<>();

        public PatientBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public PatientBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public PatientBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PatientBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PatientBuilder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PatientBuilder setVisitIdList(List<Long> visitIdList) {
            this.visitIdList = visitIdList;
            return this;
        }

        public PatientBuilder setVisitList(List<Visit> visitList) {
            this.visitList = visitList;
            return this;
        }

        public PatientBuilder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Patient build() {
            return new Patient(id, code, firstName, lastName, birthDate, gender, visitIdList, visitList);
        }
    }
}
