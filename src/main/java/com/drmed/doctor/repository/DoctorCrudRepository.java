package com.drmed.doctor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface DoctorCrudRepository extends CrudRepository<DoctorHibernate, Long> {

    Optional<DoctorHibernate> findById(Long doctorId);
    Iterable<DoctorHibernate> findByPrimaryIdContains(String primaryId);
    Iterable<DoctorHibernate> findByFirstNameContains(String firstName);
    Iterable<DoctorHibernate> findByLastNameContains(String lastName);
    Iterable<DoctorHibernate> findByFirstNameContainsAndLastNameContains(String firstName, String lastName);
    Iterable<DoctorHibernate> findAll();
    <S extends DoctorHibernate> S save(S doctorHibernate);
}
