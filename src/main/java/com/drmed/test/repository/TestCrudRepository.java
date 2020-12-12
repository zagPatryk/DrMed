package com.drmed.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface TestCrudRepository extends CrudRepository<TestHibernate, Long> {
    Iterable<TestHibernate> findByNameContaining(String name);
    Iterable<TestHibernate> findByCodeContaining(String code);
    Optional<TestHibernate> findById(Long id);
    Iterable<TestHibernate> findAll();
    <S extends TestHibernate> S save(S testHibernate);
}
