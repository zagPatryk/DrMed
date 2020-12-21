package com.drmed.api.apimedic.data.diagnosis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Diagnosis {
    private final String name;
    private final String professionalName;
    private final String icdCode;
    private final String icdName;
    private final String specialistName;

    public static class DiagnosisBuilder {
        private String name;
        private String professionalName;
        private String icdCode;
        private String icdName;
        private String specialistName;

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

        public DiagnosisBuilder setSpecialistName(String specialistName) {
            this.specialistName = specialistName;
            return this;
        }

        public Diagnosis build() {
            return new Diagnosis(name, professionalName, icdCode, icdName, specialistName);
        }
    }
}
