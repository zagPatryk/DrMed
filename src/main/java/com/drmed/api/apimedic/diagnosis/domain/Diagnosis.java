package com.drmed.api.apimedic.diagnosis.domain;

import com.drmed.base.visit.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {
    private Long id;
    private String name;
    private String professionalName;
    private String icdCode;
    private String icdName;
    private List<String> specialistNameList;
    private Visit visit;

    public Diagnosis(Long id, String name, String professionalName, String icdCode, String icdName, List<String> specialistNameList) {
        this.id = id;
        this.name = name;
        this.professionalName = professionalName;
        this.icdCode = icdCode;
        this.icdName = icdName;
        this.specialistNameList = specialistNameList;
    }

    public static class DiagnosisBuilder {
        private Long id;
        private String name;
        private String professionalName;
        private String icdCode;
        private String icdName;
        private List<String> specialistNameList;

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

        public Diagnosis build() {
            return new Diagnosis(id, name, professionalName, icdCode, icdName, specialistNameList);
        }
    }
}
