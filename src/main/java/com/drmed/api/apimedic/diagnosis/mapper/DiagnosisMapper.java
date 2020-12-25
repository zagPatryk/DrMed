package com.drmed.api.apimedic.diagnosis.mapper;

import com.drmed.api.apimedic.diagnosis.domain.Diagnosis;
import com.drmed.api.apimedic.diagnosis.dto.DiagnosisDto;
import com.drmed.api.apimedic.diagnosis.repository.DiagnosisHibernate;
import com.drmed.api.apimedic.diagnosis.response.DiagnosisResponse;
import com.drmed.api.apimedic.diagnosis.response.SpecialisationResponse;
import com.drmed.base.visit.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class DiagnosisMapper {
    @Autowired
    private VisitMapper visitMapper;

    public Diagnosis mapToDiagnosis(DiagnosisResponse diagnosisResponse) {
        return diagnosisResponse == null ? null : new Diagnosis.DiagnosisBuilder()
                .setName(diagnosisResponse.getIssueResponse().getName())
                .setProfessionalName(diagnosisResponse.getIssueResponse().getProfessionalName())
                .setIcdCode(diagnosisResponse.getIssueResponse().getIcd())
                .setIcdName(diagnosisResponse.getIssueResponse().getIcdName())
                .setSpecialistNameList(Arrays.stream(diagnosisResponse.getSpecialisationResponseChar())
                        .map(SpecialisationResponse::getName).collect(Collectors.toList()))
                .build();
    }

    public Diagnosis mapToDiagnosis(DiagnosisHibernate diagnosisHibernate) {
        return diagnosisHibernate == null ? null : new Diagnosis.DiagnosisBuilder()
                .setId(diagnosisHibernate.getId())
                .setName(diagnosisHibernate.getName())
                .setProfessionalName(diagnosisHibernate.getProfessionalName())
                .setIcdCode(diagnosisHibernate.getIcdCode())
                .setIcdName(diagnosisHibernate.getIcdName())
                .setSpecialistNameList(diagnosisHibernate.getSpecialistNameList())
                .build();
    }

    public DiagnosisHibernate mapToDiagnosisHibernate(Diagnosis diagnosis) {
        return diagnosis == null ? null : new DiagnosisHibernate(
                diagnosis.getId(),
                diagnosis.getName(),
                diagnosis.getProfessionalName(),
                diagnosis.getIcdCode(),
                diagnosis.getIcdName(),
                diagnosis.getSpecialistNameList(),
                visitMapper.mapToVisitHibernate(diagnosis.getVisit())
        );
    }

    public DiagnosisDto mapToDiagnosisDto(Diagnosis diagnosis) {
        return diagnosis == null ? null : new DiagnosisDto.DiagnosisDtoBuilder()
                .setName(diagnosis.getName())
                .setProfessionalName(diagnosis.getProfessionalName())
                .setIcdCode(diagnosis.getIcdCode())
                .setIcdName(diagnosis.getIcdName())
                .setSpecialistNameList(diagnosis.getSpecialistNameList())
                .build();
    }
}
