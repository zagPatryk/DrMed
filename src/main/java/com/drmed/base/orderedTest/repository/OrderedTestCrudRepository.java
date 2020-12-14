package com.drmed.base.orderedTest.repository;

import com.drmed.base.workstation.repository.WorkstationHibernate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface OrderedTestCrudRepository extends CrudRepository<OrderedTestHibernate, Long> {

    Optional<OrderedTestHibernate> findById(Long id);
    Iterable<OrderedTestHibernate> findAllByOrder_Id(Long id);
    <S extends WorkstationHibernate> S save(S workstationHibernate);
}
