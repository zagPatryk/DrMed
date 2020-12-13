package com.drmed.trello.service;

import com.drmed.additional.exceptions.trelloException.TrelloListNotFoundException;
import com.drmed.additional.statuses.ResultStatus;
import com.drmed.doctor.dto.DoctorInfoDto;
import com.drmed.order.dto.OrderInfoDto;
import com.drmed.order.dto.OrderInfoTrelloDto;
import com.drmed.orderedTest.dto.OrderedTestInfoDto;
import com.drmed.trello.client.TrelloClient;
import com.drmed.trello.dto.TrelloCardDto;
import com.drmed.trello.dto.TrelloListDto;
import com.drmed.trello.newObjects.NewTrelloBoardDto;
import com.drmed.trello.newObjects.NewTrelloCardDto;
import com.drmed.trello.response.CreatedTrelloBoardDto;
import com.drmed.trello.response.CreatedTrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TrelloService {
    @Autowired
    private TrelloClient trelloClient;

    public List<TrelloCardDto> getPendingOrdersForDoctor(String trelloDoctorBoardId) throws TrelloListNotFoundException {
        String listId = getToDoListId(trelloDoctorBoardId);
        return Arrays.asList(trelloClient.getAllCardsOnList(listId));
    }

    public CreatedTrelloBoardDto createBoardForDoctor(DoctorInfoDto doctorInfoDto) {
        String name = doctorInfoDto.getPrimaryId() + " board with orders";
        String description = "Doctor name: " + doctorInfoDto.getFirstName() + " " + doctorInfoDto.getLastName();
        NewTrelloBoardDto newTrelloBoardDto = new NewTrelloBoardDto(name, description);
        return trelloClient.createNewBoard(newTrelloBoardDto);
    }

    public CreatedTrelloCardDto createCardForOrder(String trelloDoctorBoardId, OrderInfoDto orderInfoDto) throws TrelloListNotFoundException {
        String name = "Order: " + orderInfoDto.getCode();
        String description = createOrderDescription(orderInfoDto);
        NewTrelloCardDto newTrelloBoardDto = new NewTrelloCardDto(name, description, getToDoListId(trelloDoctorBoardId));
        return trelloClient.createNewCard(newTrelloBoardDto);
    }

    public TrelloCardDto changeStatusOfOrder(String trelloOrderCardId, ResultStatus resultStatus) throws TrelloListNotFoundException {
        TrelloCardDto trelloCardDto = trelloClient.getTrelloCardById(trelloOrderCardId);
        if(resultStatus == ResultStatus.PENDING || resultStatus == ResultStatus.TEMPORARY) {
            trelloCardDto.setListId(getToDoListId(trelloCardDto.getBoardId()));
        } else {
            trelloCardDto.setListId(getFinishedListId(trelloCardDto.getBoardId()));
        }
        return trelloClient.updateCard(trelloCardDto);
    }

    public void deleteCardWithOrder(String cardId) {
        trelloClient.deleteCard(cardId);
    }

    public TrelloCardDto updateTrelloCardDescription(OrderInfoTrelloDto orderInfoTrelloDto) {
        TrelloCardDto trelloCardDto = trelloClient.getTrelloCardById(orderInfoTrelloDto.getTrelloCardId());
        trelloCardDto.setDescription(createOrderDescription(orderInfoTrelloDto));
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
