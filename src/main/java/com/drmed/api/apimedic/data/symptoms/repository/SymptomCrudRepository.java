package com.drmed.api.apimedic.data.symptoms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SymptomCrudRepository extends CrudRepository<SymptomHibernate, Long> {

    Optional<SymptomHibernate> findById(Long symptomInternalId);
    Optional<SymptomHibernate> findBySymptomId(Long symptomExternalId);
    Iterable<SymptomHibernate> findAll();
    <S extends SymptomHibernate> S save(S entity);
}
