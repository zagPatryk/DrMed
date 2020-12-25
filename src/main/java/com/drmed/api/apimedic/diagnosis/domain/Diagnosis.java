package com.drmed.api.apimedic.diagnosis.domain;

import com.drmed.base.visit.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Diagnosis {
    private Long id;
    private String name;
    private String professionalName;
    private String icdCode;
    private String icdName;
    private List<String> specialistNameList;
    private Visit visit;

    public static class DiagnosisBuilder {
        private Long id;
        private String name;
        private String professionalName;
        private String icdCode;
        private String icdName;
        private List<String> specialistNameList;
        private Visit visit;

        public DiagnosisBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public DiagnosisBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DiagnosisBuilder setProfessionalName(String professionalName) {
            this.professionalName = professionalName;
            return this;
        }

        public DiagnosisBuilder setIcdCode(String icdCode) {
            this.icdCode = icdCode;
            return this;
        }

        public DiagnosisBuilder setIcdName(String icdName) {
            this.icdName = icdName;
            return this;
        }

        public DiagnosisBuilder setSpecialistNameList(List<String> specialistNameList) {
            this.specialistNameList = specialistNameList;
            return this;
        }

        public DiagnosisBuilder setVisit(Visit visit) {
            this.visit = visit;
            return this;
        }

        public Diagnosis build() {
            return new Diagnosis(id, name, professionalName, icdCode, icdName, specialistNameList, visit);
        }
    }
}
