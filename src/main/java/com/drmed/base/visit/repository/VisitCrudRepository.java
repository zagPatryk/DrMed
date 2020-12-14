package com.drmed.base.visit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitCrudRepository extends CrudRepository<VisitHibernate, Long> {

    Optional<VisitHibernate> findById(Long visitId);
    Iterable<VisitHibernate> findAllByPatient_Id(Long patientId);
    Iterable<VisitHibernate> findAllByCodeContains(String code);
    <S extends VisitHibernate> S save(S entity);
}
