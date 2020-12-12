package com.drmed.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface OrderCrudRepository extends CrudRepository<OrderHibernate, Long> {

    <S extends OrderHibernate> S save(S orderHibernate);
    Optional<OrderHibernate> findById(Long aLong);
    Iterable<OrderHibernate> findAllByPatient_Id(Long patientId);
    Iterable<OrderHibernate> findAllByCodeContains(Integer code);
}
