package com.drmed.api.apimedic.symptoms.mapper;

import com.drmed.api.apimedic.symptoms.domain.Symptom;
import com.drmed.api.apimedic.symptoms.dto.SymptomDto;
import com.drmed.api.apimedic.symptoms.repository.SymptomHibernate;
import com.drmed.api.apimedic.symptoms.response.SymptomResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SymptomMapper {

    public Symptom mapToSymptom(SymptomHibernate symptomHibernate) {
        return new Symptom(
                symptomHibernate.getId(),
                symptomHibernate.getSymptomId(),
                symptomHibernate.getName());
    }

    public SymptomHibernate mapToSymptomHibernate(Symptom symptom) {
        return new SymptomHibernate(
                symptom.getInternalId() != null
                        ? symptom.getInternalId()
                        : null,
                symptom.getExternalId(),
                symptom.getName()
        );
    }

    public Symptom mapToSymptom(SymptomResponse symptomResponse) {
        return new Symptom(
                symptomResponse.getId(),
                symptomResponse.getName());
    }

    public SymptomDto mapToSymptomDto(Symptom symptom) {
        return new SymptomDto(
                symptom.getInternalId(),
                symptom.getExternalId(),
                symptom.getName());
    }

    public Symptom mapToSymptom(SymptomDto symptomDto) {
        return new Symptom(
                symptomDto.getInternalId(),
                symptomDto.getExternalId(),
                symptomDto.getName());
    }

    public List<SymptomDto> mapToSymptomDtoList(List<Symptom> symptomList) {
        return symptomList == null
                ? null : symptomList.stream().map(this::mapToSymptomDto).collect(Collectors.toList());
    }

    public List<Symptom> mapToSymptomListFromResponse(List<SymptomResponse> symptomResponseList) {
        return symptomResponseList == null
                ? null : symptomResponseList.stream().map(this::mapToSymptom).collect(Collectors.toList());
    }

    public List<Symptom> mapToSymptomList(List<SymptomHibernate> symptomHibernateList) {
        return symptomHibernateList == null
                ? null : symptomHibernateList.stream().map(this::mapToSymptom).collect(Collectors.toList());
    }

    public List<Symptom> mapToSymptomListFromDto(List<SymptomDto> symptomDtoList) {
        return symptomDtoList == null
                ? null : symptomDtoList.stream().map(this::mapToSymptom).collect(Collectors.toList());
    }

    public List<SymptomHibernate> mapToSymptomHibernateList(List<Symptom> symptomList) {
        return symptomList == null
                ? null : symptomList.stream().map(this::mapToSymptomHibernate).collect(Collectors.toList());
    }
}
