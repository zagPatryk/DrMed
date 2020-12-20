package com.drmed.api.trello.repository;

import com.drmed.api.trello.exception.trelloException.TrelloBoardNotFoundException;
import com.drmed.api.trello.exception.trelloException.TrelloCardNotFoundException;
import com.drmed.api.trello.repository.board.TrelloBoardIdCrudRepository;
import com.drmed.api.trello.repository.board.TrelloBoardIdHibernate;
import com.drmed.api.trello.repository.card.TrelloCardIdCrudRepository;
import com.drmed.api.trello.repository.card.TrelloCardIdHibernate;
import com.drmed.api.trello.response.CreatedTrelloBoardDto;
import com.drmed.api.trello.response.CreatedTrelloCardDto;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.base.doctor.repository.DoctorCrudRepository;
import com.drmed.base.order.repository.OrderCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrelloRepository {
    @Autowired
    private TrelloBoardIdCrudRepository trelloBoardIdCrudRepository;
    @Autowired
    private TrelloCardIdCrudRepository trelloCardIdCrudRepository;
    @Autowired
    private DoctorCrudRepository doctorCrudRepository;
    @Autowired
    private OrderCrudRepository orderCrudRepository;

    public String getTrelloBoardIdForDoctor(Long doctorId) throws TrelloBoardNotFoundException {
        return trelloBoardIdCrudRepository.findByDoctor_Id(doctorId)
                .orElseThrow(TrelloBoardNotFoundException::new).getTrelloBoardId();
    }

    public String getTrelloCardIdForOrder(Long patientId) throws TrelloCardNotFoundException {
        return trelloCardIdCrudRepository.findByOrder_Id(patientId)
                .orElseThrow(TrelloCardNotFoundException::new).getTrelloBoardId();
    }

    public CreatedTrelloBoardDto saveTrelloBoardIdForDoctor(Long doctorId, CreatedTrelloBoardDto createdTrelloBoardDto) throws DoctorNotFoundException {
        TrelloBoardIdHibernate trelloBoardIdHibernate = new TrelloBoardIdHibernate(createdTrelloBoardDto.getId(),
                doctorCrudRepository.findById(doctorId).orElseThrow(DoctorNotFoundException::new));
        trelloBoardIdCrudRepository.save(trelloBoardIdHibernate);
        return createdTrelloBoardDto;
    }

    public CreatedTrelloCardDto saveTrelloCardIdForOrder(Long orderId, CreatedTrelloCardDto createdTrelloCardDto) throws OrderNotFoundException {
        TrelloCardIdHibernate trelloCardIdHibernate = new TrelloCardIdHibernate(createdTrelloCardDto.getId(),
                orderCrudRepository.findById(orderId).orElseThrow(OrderNotFoundException::new));
        trelloCardIdCrudRepository.save(trelloCardIdHibernate);
        return createdTrelloCardDto;
    }
}
