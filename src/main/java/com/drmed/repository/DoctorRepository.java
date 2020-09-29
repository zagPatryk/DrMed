package com.drmed.repository;

import com.drmed.domain.doctor.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Override
    void deleteById(Long id);

    // QUERY
}
