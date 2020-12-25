package com.drmed.api.apimedic.diagnosis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiagnosisDto {
    private String name;
    private String professionalName;
    private String icdCode;
    private String icdName;
    private List<String> specialistNameList;

    public static class DiagnosisDtoBuilder {
        private String name;
        private String professionalName;
        private String icdCode;
        private String icdName;
        private List<String> specialistNameList;

        public DiagnosisDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DiagnosisDtoBuilder setProfessionalName(String professionalName) {
            this.professionalName = professionalName;
            return this;
        }

        public DiagnosisDtoBuilder setIcdCode(String icdCode) {
            this.icdCode = icdCode;
            return this;
        }

        public DiagnosisDtoBuilder setIcdName(String icdName) {
            this.icdName = icdName;
            return this;
        }

        public DiagnosisDtoBuilder setSpecialistNameList(List<String> specialistNameList) {
            this.specialistNameList = specialistNameList;
            return this;
        }

        public DiagnosisDto build() {
            return new DiagnosisDto(name, professionalName, icdCode, icdName, specialistNameList);
        }
    }
}
