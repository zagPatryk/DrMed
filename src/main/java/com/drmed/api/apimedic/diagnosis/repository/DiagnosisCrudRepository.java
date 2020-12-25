package com.drmed.api.apimedic.diagnosis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosisCrudRepository extends CrudRepository<DiagnosisHibernate, Long> {

    Optional<DiagnosisHibernate> findById(Long diagnosisId);
    Optional<DiagnosisHibernate> findByVisit_Id(Long visitId);
    <S extends DiagnosisHibernate> S save(S entity);
}
