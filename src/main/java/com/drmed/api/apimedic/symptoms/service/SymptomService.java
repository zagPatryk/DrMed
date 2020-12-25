package com.drmed.api.apimedic.symptoms.service;

import com.drmed.api.apimedic.additional.exception.dataNotFoundInDatabase.SymptomNotFoundException;
import com.drmed.api.apimedic.client.ApiMedicClient;
import com.drmed.api.apimedic.symptoms.domain.Symptom;
import com.drmed.api.apimedic.symptoms.dto.SymptomDto;
import com.drmed.api.apimedic.symptoms.mapper.SymptomMapper;
import com.drmed.api.apimedic.symptoms.repository.SymptomRepository;
import com.drmed.api.apimedic.symptoms.response.SymptomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SymptomService {
    @Autowired
    private SymptomRepository symptomRepository;
    @Autowired
    private SymptomMapper symptomMapper;
    @Autowired
    private ApiMedicClient apiMedicClient;

    public List<Symptom> downloadSymptomsToBase() throws InvalidKeyException, NoSuchAlgorithmException {
        List<Symptom> symptomList = new ArrayList<>();
        SymptomResponse[] symptomResponseChar = apiMedicClient.downloadAllSymptoms();
        if (symptomResponseChar.length > 0) {
            symptomRepository.deleteAllSymptoms();
            for (SymptomResponse symptomResponse : symptomResponseChar) {
                Symptom symptom = symptomMapper.mapToSymptom(symptomResponse);
                symptomList.add(symptomRepository.saveSymptom(symptom));
            }
        }
        return symptomList;
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
