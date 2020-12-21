package com.drmed.api.apimedic.data.symptoms.repository;

import com.drmed.api.apimedic.data.symptoms.domain.Symptom;
import com.drmed.api.apimedic.data.symptoms.mapper.SymptomMapper;
import com.drmed.api.apimedic.data.symptoms.response.SymptomResponse;
import com.drmed.api.apimedic.exception.dataNotFoundInDatabase.SymptomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SymptomRepository {
    @Autowired
    private SymptomCrudRepository symptomCrudRepository;
    @Autowired
    private SymptomMapper symptomMapper;

    public Symptom findSymptomByInternalId(Long internalId) throws SymptomNotFoundException {
        SymptomHibernate symptomHibernate = symptomCrudRepository.findById(internalId)
                .orElseThrow(SymptomNotFoundException::new);
        return symptomMapper.mapToSymptom(symptomHibernate);
    }

    public Symptom findSymptomByExternalId(Long externalIdd) throws SymptomNotFoundException {
        SymptomHibernate symptomHibernate = symptomCrudRepository.findBySymptomId(externalIdd)
                .orElseThrow(SymptomNotFoundException::new);
        return symptomMapper.mapToSymptom(symptomHibernate);
    }

    public List<Symptom> findAllSymptoms() {
        List<SymptomHibernate> symptomHibernateList = new ArrayList<>();
        symptomCrudRepository.findAll().forEach(symptomHibernateList::add);
        return symptomMapper.mapToSymptomList(symptomHibernateList);
    }

    public List<Symptom> saveSymptomList(List<SymptomResponse> symptomResponseList) {
        List<SymptomHibernate> symptomHibernateList = symptomMapper.mapToSymptomHibernateList(symptomResponseList);
        symptomHibernateList.forEach(symptomHibernate -> symptomCrudRepository.save(symptomHibernate));
        return symptomMapper.mapToSymptomList(symptomHibernateList);
    }
}
