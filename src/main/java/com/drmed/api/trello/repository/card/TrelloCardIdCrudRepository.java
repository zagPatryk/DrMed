package com.drmed.api.trello.repository.card;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface TrelloCardIdCrudRepository extends CrudRepository<TrelloCardIdHibernate, Long> {

    Optional<TrelloCardIdHibernate> findByOrder_Id(Long orderId);
    <S extends TrelloCardIdHibernate> S save(S trelloCardHibernate);
}