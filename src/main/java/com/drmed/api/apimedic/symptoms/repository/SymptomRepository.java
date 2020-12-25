package com.drmed.api.apimedic.symptoms.repository;

import com.drmed.api.apimedic.exception.dataNotFoundInDatabase.SymptomNotFoundException;
import com.drmed.api.apimedic.symptoms.domain.Symptom;
import com.drmed.api.apimedic.symptoms.mapper.SymptomMapper;
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

    public Symptom getSymptomByInternalId(Long internalId) throws SymptomNotFoundException {
        SymptomHibernate symptomHibernate = symptomCrudRepository.findById(internalId)
                .orElseThrow(SymptomNotFoundException::new);
        return symptomMapper.mapToSymptom(symptomHibernate);
    }

    public Symptom getSymptomByExternalId(Long externalIdd) throws SymptomNotFoundException {
        SymptomHibernate symptomHibernate = symptomCrudRepository.findBySymptomId(externalIdd)
                .orElseThrow(SymptomNotFoundException::new);
        return symptomMapper.mapToSymptom(symptomHibernate);
    }

    public List<Symptom> getAllSymptoms() {
        List<SymptomHibernate> symptomHibernateList = new ArrayList<>();
        symptomCrudRepository.findAll().forEach(symptomHibernateList::add);
        return symptomMapper.mapToSymptomList(symptomHibernateList);
    }

    public Symptom saveSymptom(Symptom symptom) {
        SymptomHibernate symptomHibernate = symptomMapper.mapToSymptomHibernate(symptom);
        symptomCrudRepository.save(symptomHibernate);
        return symptomMapper.mapToSymptom(symptomHibernate);
    }

    public void deleteAllSymptoms() {
        symptomCrudRepository.deleteAll();
    }
}
