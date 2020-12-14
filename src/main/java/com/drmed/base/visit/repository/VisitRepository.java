package com.drmed.base.visit.repository;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.visit.domain.Visit;
import com.drmed.base.visit.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisitRepository {
    @Autowired
    private VisitCrudRepository visitCrudRepository;
    @Autowired
    private VisitMapper visitMapper;

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
}
