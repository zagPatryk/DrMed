package com.drmed.api.trello.repository.board;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface TrelloBoardIdCrudRepository extends CrudRepository<TrelloBoardIdHibernate, Long> {

    Optional<TrelloBoardIdHibernate> findByDoctor_Id(Long doctorId);
    <S extends TrelloBoardIdHibernate> S save(S trelloBoardHibernate);
}