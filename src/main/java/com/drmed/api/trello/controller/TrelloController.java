package com.drmed.api.trello.controller;


import com.drmed.api.trello.exception.TrelloException;
import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.api.trello.dto.TrelloCardDto;
import com.drmed.api.trello.response.CreatedTrelloBoardDto;
import com.drmed.api.trello.response.CreatedTrelloCardDto;
import com.drmed.api.trello.service.TrelloService;
import com.drmed.base.order.dto.OrderInfoTrelloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
    private TrelloService trelloService;

    @GetMapping(value = "getPendingOrdersForDoctor")
    public List<TrelloCardDto> getPendingOrdersForDoctor(@RequestParam String trelloBoardId) throws TrelloException {
        return trelloService.getPendingOrdersForDoctor(trelloBoardId);
    }

    @PostMapping(value = "createBoardForDoctor")
    public CreatedTrelloBoardDto createBoardForDoctor(@RequestBody DoctorInfoDto doctorInfoDto) {
        return trelloService.createBoardForDoctor(doctorInfoDto);
    }

    @PostMapping(value = "createCardForOrder")
    public CreatedTrelloCardDto createCardForOrder(@RequestParam String trelloDoctorBoardId, @RequestBody OrderInfoDto orderInfoDto) throws TrelloException {
        return trelloService.createCardForOrder(trelloDoctorBoardId, orderInfoDto);
    }

    @PutMapping(value = "changeStatusOfOrder")
    public TrelloCardDto changeStatusOfOrder(@RequestParam String trelloOrderCardId, @RequestParam ResultStatus resultStatus) throws TrelloException {
        return trelloService.changeStatusOfOrder(trelloOrderCardId, resultStatus);
    }

    @PutMapping(value = "updateCardDescription")
    public TrelloCardDto changeStatusOfOrder(@RequestBody OrderInfoTrelloDto orderInfoTrelloDto) {
        return trelloService.updateTrelloCardDescription(orderInfoTrelloDto);
    }

    @DeleteMapping(value = "deleteCardWithOrder")
    public void deleteCardWithOrder(String cardId) {
        trelloService.deleteCardWithOrder(cardId);
    }
}
