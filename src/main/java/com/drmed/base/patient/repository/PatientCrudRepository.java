package com.drmed.base.patient.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface PatientCrudRepository extends CrudRepository<PatientHibernate, Long> {
    Optional<PatientHibernate> findById(Long PatientId);
    Iterable<PatientHibernate> findByMRNContains(String MRN);
    Iterable<PatientHibernate> findByFirstNameContains(String firstname);
    Iterable<PatientHibernate> findByLastNameContains(String lastName);
    Iterable<PatientHibernate> findByFirstNameContainsAndLastNameContains(String firstName, String lastName);
    Iterable<PatientHibernate> findAll();
    <S extends PatientHibernate> S save(S entity);
}