package com.drmed.repository;

import com.drmed.domain.workstation.Workstation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface WorkstationRepository extends CrudRepository<Workstation, Long> {

}
