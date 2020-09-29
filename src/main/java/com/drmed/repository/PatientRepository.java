package com.drmed.repository;

import com.drmed.domain.patient.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

}
