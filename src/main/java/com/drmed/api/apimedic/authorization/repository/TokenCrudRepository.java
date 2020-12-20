package com.drmed.api.apimedic.authorization.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TokenCrudRepository extends CrudRepository<TokenHibernate, Long> {

    Iterable<TokenHibernate> findAll();
    <S extends TokenHibernate> S save(S TokenHibernate);
    void deleteAll();
}