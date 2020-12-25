package com.drmed.api.apimedic.diagnosis.repository;

import com.drmed.api.apimedic.diagnosis.domain.Diagnosis;
import com.drmed.api.apimedic.diagnosis.mapper.DiagnosisMapper;
import com.drmed.api.apimedic.exception.dataNotFoundInDatabase.DiagnosisNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiagnosisRepository {
    @Autowired
    private DiagnosisMapper diagnosisMapper;
    @Autowired
    private DiagnosisCrudRepository diagnosisCrudRepository;

    public Diagnosis getDiagnosisById(Long diagnosisId) throws DiagnosisNotFoundException {
        return diagnosisMapper.mapToDiagnosis(diagnosisCrudRepository.findById(diagnosisId).orElseThrow(DiagnosisNotFoundException::new));
    }

    public Diagnosis getDiagnosisForVisit(Long visitId) throws DiagnosisNotFoundException {
        return diagnosisMapper.mapToDiagnosis(diagnosisCrudRepository.findByVisit_Id(visitId).orElseThrow(DiagnosisNotFoundException::new));
    }

    public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
        DiagnosisHibernate diagnosisHibernate = diagnosisMapper.mapToDiagnosisHibernate(diagnosis);
        diagnosisCrudRepository.save(diagnosisHibernate);
        return diagnosisMapper.mapToDiagnosis(diagnosisHibernate);
    }
}
