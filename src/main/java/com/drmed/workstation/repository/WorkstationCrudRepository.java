package com.drmed.workstation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface WorkstationCrudRepository extends CrudRepository<WorkstationHibernate, Long> {
    Iterable<WorkstationHibernate> findByNameContaining(String name);
    Iterable<WorkstationHibernate> findByCodeContaining(String code);
    Optional<WorkstationHibernate> findById(Long id);
    Iterable<WorkstationHibernate> findAll();
    <S extends WorkstationHibernate> S save(S workstationHibernate);
}
