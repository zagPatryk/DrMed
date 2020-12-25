package com.drmed.base.visit.repository;

import com.drmed.api.apimedic.diagnosis.domain.Diagnosis;
import com.drmed.api.apimedic.diagnosis.mapper.DiagnosisMapper;
import com.drmed.api.apimedic.diagnosis.repository.DiagnosisHibernate;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.visit.domain.Visit;
import com.drmed.base.visit.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class VisitRepository {
    @Autowired
    private VisitCrudRepository visitCrudRepository;
    @Autowired
    private VisitMapper visitMapper;
    @Autowired
    private DiagnosisMapper diagnosisMapper;

    @Transactional
    public Visit getVisitById(Long visitId) throws VisitNotFoundException {
        return visitMapper.mapToVisit(visitCrudRepository.findById(visitId).orElseThrow(VisitNotFoundException::new));
    }

    public List<Visit> getAllVisitsForPatient(Long patientId) {
        List<VisitHibernate> visitHibernateList = new ArrayList<>();
        visitCrudRepository.findAllByPatient_Id(patientId).forEach(visitHibernateList::add);
        return visitMapper.mapToVisitList(visitHibernateList);
    }

    public List<Visit> getAllByCodeContains(String code) {
        List<VisitHibernate> visitHibernateList = new ArrayList<>();
        visitCrudRepository.findAllByCodeContains(code).forEach(visitHibernateList::add);
        return visitMapper.mapToVisitList(visitHibernateList);
    }

    public Visit saveVisit(Visit visit) {
        VisitHibernate visitHibernate = visitMapper.mapToVisitHibernate(visit);
        visitCrudRepository.save(visitHibernate);
        return visitMapper.mapToVisit(visitHibernate);
    }

    public Visit saveDiagnosisForVisit(Diagnosis diagnosis, Visit visit) {
        DiagnosisHibernate diagnosisHibernate = diagnosisMapper.mapToDiagnosisHibernate(diagnosis);
        VisitHibernate visitHibernate = visitMapper.mapToVisitHibernate(visit);
        diagnosisHibernate.setVisit(visitHibernate);
        visitHibernate.setDiagnosis(diagnosisHibernate);
        visitCrudRepository.save(visitHibernate);
       return visitMapper.mapToVisit(visitHibernate);
    }
}
