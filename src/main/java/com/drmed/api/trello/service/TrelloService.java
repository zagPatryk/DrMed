package com.drmed.api.trello.service;

import com.drmed.api.trello.client.TrelloClient;
import com.drmed.api.trello.dto.TrelloCardDto;
import com.drmed.api.trello.dto.TrelloListDto;
import com.drmed.api.trello.exception.trelloException.TrelloBoardNotFoundException;
import com.drmed.api.trello.exception.trelloException.TrelloCardNotFoundException;
import com.drmed.api.trello.exception.trelloException.TrelloListNotFoundException;
import com.drmed.api.trello.newObjects.NewTrelloBoardDto;
import com.drmed.api.trello.newObjects.NewTrelloCardDto;
import com.drmed.api.trello.repository.TrelloRepository;
import com.drmed.api.trello.response.CreatedTrelloBoardDto;
import com.drmed.api.trello.response.CreatedTrelloCardDto;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.base.order.dto.OrderInfoWithDoctorDto;
import com.drmed.base.orderedTest.dto.OrderedTestInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TrelloService {
    @Autowired
    private TrelloRepository trelloRepository;
    @Autowired
    private TrelloClient trelloClient;

    public List<TrelloCardDto> getPendingOrdersForDoctor(Long doctorId) throws TrelloListNotFoundException, TrelloBoardNotFoundException {
        String listId = getToDoListId(trelloRepository.getTrelloBoardIdForDoctor(doctorId));
        return Arrays.asList(trelloClient.getAllCardsOnList(listId));
    }

    public CreatedTrelloBoardDto createBoardForDoctor(DoctorInfoDto doctorInfoDto) throws DoctorNotFoundException {
        String name = doctorInfoDto.getCode() + " board with orders";
        String description = "Doctor name: " + doctorInfoDto.getFirstName() + " " + doctorInfoDto.getLastName();
        NewTrelloBoardDto newTrelloBoardDto = new NewTrelloBoardDto(name, description);
        return trelloRepository.saveTrelloBoardIdForDoctor(doctorInfoDto.getId(), trelloClient.createNewBoard(newTrelloBoardDto));
    }

    public CreatedTrelloCardDto createCardForOrder(OrderInfoWithDoctorDto orderInfoWithDoctorDto)
            throws TrelloListNotFoundException, TrelloBoardNotFoundException, OrderNotFoundException {
        String name = "Order: " + orderInfoWithDoctorDto.getCode();
        String description = createOrderDescription(orderInfoWithDoctorDto);
        NewTrelloCardDto newTrelloBoardDto = new NewTrelloCardDto(name, description,
                        getToDoListId(trelloRepository.getTrelloBoardIdForDoctor(orderInfoWithDoctorDto.getDoctorId())));
        return trelloRepository.saveTrelloCardIdForOrder(orderInfoWithDoctorDto.getId(), trelloClient.createNewCard(newTrelloBoardDto));
    }

    public TrelloCardDto changeStatusOfOrder(Long orderId, ResultStatus resultStatus) throws TrelloListNotFoundException, TrelloCardNotFoundException {
        TrelloCardDto trelloCardDto = trelloClient.getTrelloCardById(trelloRepository.getTrelloCardIdForOrder(orderId));
        if(resultStatus == ResultStatus.PENDING) {
            trelloCardDto.setListId(getToDoListId(trelloCardDto.getBoardId()));
        } else {
            trelloCardDto.setListId(getFinishedListId(trelloCardDto.getBoardId()));
        }
        return trelloClient.updateCard(trelloCardDto);
    }

    public void deleteCardWithOrder(Long orderId) throws TrelloCardNotFoundException {
        trelloClient.deleteCard(trelloRepository.getTrelloCardIdForOrder(orderId));
    }

    public void deleteBoardForDoctor(Long doctorId) throws TrelloBoardNotFoundException {
        trelloClient.deleteBoard(trelloRepository.getTrelloBoardIdForDoctor(doctorId));
    }

    public TrelloCardDto updateTrelloCardDescription(OrderInfoDto orderInfoDto) throws TrelloCardNotFoundException {
        TrelloCardDto trelloCardDto = trelloClient.getTrelloCardById(trelloRepository.getTrelloCardIdForOrder(orderInfoDto.getId()));
        trelloCardDto.setDescription(createOrderDescription(orderInfoDto));
        return trelloClient.updateCard(trelloCardDto);
    }

    private String getToDoListId(String trelloDoctorBoardId) throws TrelloListNotFoundException {
        for (TrelloListDto listDto : trelloClient.getAllListsOnBoard(trelloDoctorBoardId)) {
            if (listDto.getName().equals("Do zrobienia") || listDto.getName().equals("To Do")) {
                return listDto.getId();
            }
        }
        throw new TrelloListNotFoundException();
    }

    private String getFinishedListId(String trelloDoctorBoardId) throws TrelloListNotFoundException {
        for (TrelloListDto listDto : trelloClient.getAllListsOnBoard(trelloDoctorBoardId)) {
            if (listDto.getName().equals("Zrobione") || listDto.getName().equals("Done")) {
                return listDto.getId();
            }
        }
        throw new TrelloListNotFoundException();
    }

    private String createOrderDescription(OrderInfoDto orderInfoDto) {
        StringBuilder descriptionBuilder = new StringBuilder("Ordered tests: \n");
        for (OrderedTestInfoDto orderedTestInfoDto : orderInfoDto.getOrderedTests()) {
            descriptionBuilder
                    .append("-").append(orderedTestInfoDto.getTest().getCode()).append(" ")
                    .append(orderedTestInfoDto.getTest().getName()).append("\n");
        }
        return descriptionBuilder.toString();
    }

}
