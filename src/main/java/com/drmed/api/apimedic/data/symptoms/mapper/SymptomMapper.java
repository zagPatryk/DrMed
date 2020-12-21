package com.drmed.api.apimedic.data.symptoms.mapper;

import com.drmed.api.apimedic.data.symptoms.domain.Symptom;
import com.drmed.api.apimedic.data.symptoms.repository.SymptomHibernate;
import com.drmed.api.apimedic.data.symptoms.response.SymptomResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SymptomMapper {
    public Symptom mapToSymptom(SymptomHibernate symptomHibernate) {
        return new Symptom(
                symptomHibernate.getSymptomId(),
                symptomHibernate.getName());
    }

    public List<Symptom> mapToSymptomList(List<SymptomHibernate> symptomHibernateList) {
        return symptomHibernateList.stream().map(this::mapToSymptom).collect(Collectors.toList());
    }

    public SymptomHibernate mapToSymptomHibernate(SymptomResponse symptomResponse) {
        return new SymptomHibernate(
                symptomResponse.getId(),
                symptomResponse.getName());
    }

    public List<SymptomHibernate> mapToSymptomHibernateList(List<SymptomResponse> symptomResponseList) {
        return symptomResponseList.stream().map(this::mapToSymptomHibernate).collect(Collectors.toList());
    }
}
