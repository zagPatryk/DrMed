package com.drmed.api.apimedic.diagnosis.repository;

import com.drmed.api.apimedic.additional.exception.dataNotFoundInDatabase.DiagnosisNotFoundException;
import com.drmed.api.apimedic.diagnosis.domain.Diagnosis;
import com.drmed.api.apimedic.diagnosis.mapper.DiagnosisMapper;
import com.drmed.base.visit.domain.Visit;
import com.drmed.base.visit.mapper.VisitMapper;
import com.drmed.base.visit.repository.VisitCrudRepository;
import com.drmed.base.visit.repository.VisitHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiagnosisRepository {
    @Autowired
    private DiagnosisMapper diagnosisMapper;
    @Autowired
    private DiagnosisCrudRepository diagnosisCrudRepository;
    @Autowired
    private VisitMapper visitMapper;

    public Diagnosis getDiagnosisById(Long diagnosisId) throws DiagnosisNotFoundException {
        return diagnosisMapper.mapToDiagnosis(diagnosisCrudRepository.findById(diagnosisId).orElseThrow(DiagnosisNotFoundException::new));
    }

    public Diagnosis getDiagnosisForVisit(Long visitId) throws DiagnosisNotFoundException {
        return diagnosisMapper.mapToDiagnosis(diagnosisCrudRepository.findByVisit_Id(visitId).orElseThrow(DiagnosisNotFoundException::new));
    }
@Autowired
private VisitCrudRepository visitCrudRepository;

    public Diagnosis saveDiagnosis(Diagnosis diagnosis, Visit visit) {
        DiagnosisHibernate diagnosisHibernate = diagnosisMapper.mapToDiagnosisHibernate(diagnosis);
        VisitHibernate visitHibernate = visitMapper.mapToVisitHibernate(visit);
        diagnosisHibernate.setVisit(visitHibernate);
        visitHibernate.setDiagnosis(diagnosisHibernate);
        visitCrudRepository.save(visitHibernate);
        return diagnosisMapper.mapToDiagnosis(diagnosisHibernate);
    }
}
