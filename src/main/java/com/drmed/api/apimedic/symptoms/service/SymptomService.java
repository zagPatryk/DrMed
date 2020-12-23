package com.drmed.api.apimedic.symptoms.service;

import com.drmed.api.apimedic.exception.dataNotFoundInDatabase.SymptomNotFoundException;
import com.drmed.api.apimedic.symptoms.domain.Symptom;
import com.drmed.api.apimedic.symptoms.dto.SymptomDto;
import com.drmed.api.apimedic.symptoms.mapper.SymptomMapper;
import com.drmed.api.apimedic.symptoms.repository.SymptomRepository;
import com.drmed.api.apimedic.symptoms.response.SymptomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SymptomService {
    @Autowired
    private SymptomRepository symptomRepository;
    @Autowired
    private SymptomMapper symptomMapper;

    public Symptom addNewSymptom(SymptomResponse symptomResponse) {
        Symptom symptom = symptomMapper.mapToSymptom(symptomResponse);
        return symptomRepository.saveSymptom(symptom);
    }

    public List<Symptom> addNewSymptoms(List<SymptomResponse> symptomResponseList) {
        return symptomResponseList.stream().map(this::addNewSymptom).collect(Collectors.toList());
    }

    public List<SymptomDto> getAllSymptomDtoList() {
        return symptomMapper.mapToSymptomDtoList(symptomRepository.getAllSymptoms());
    }

    public List<Symptom> getAllSymptomList() {
        return symptomRepository.getAllSymptoms();
    }

    public Symptom getSymptomByInternalId(Long internalId) throws SymptomNotFoundException {
        return symptomRepository.getSymptomByInternalId(internalId);
    }

    public Symptom getSymptomByExternalId(Long externalId) throws SymptomNotFoundException {
        return symptomRepository.getSymptomByExternalId(externalId);
    }
}
