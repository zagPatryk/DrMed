package com.drmed.api.trello.controller;


import com.drmed.api.trello.dto.TrelloCardDto;
import com.drmed.api.trello.exception.TrelloException;
import com.drmed.api.trello.exception.trelloException.TrelloBoardNotFoundException;
import com.drmed.api.trello.exception.trelloException.TrelloCardNotFoundException;
import com.drmed.api.trello.response.CreatedTrelloBoardDto;
import com.drmed.api.trello.response.CreatedTrelloCardDto;
import com.drmed.api.trello.service.TrelloService;
import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.base.order.dto.OrderInfoWithDoctorDto;
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
    public List<TrelloCardDto> getPendingOrdersForDoctor(@RequestParam Long doctorId) throws TrelloException {
        return trelloService.getPendingOrdersForDoctor(doctorId);
    }

    @PostMapping(value = "createBoardForDoctor")
    public CreatedTrelloBoardDto createBoardForDoctor(@RequestBody DoctorInfoDto doctorInfoDto) throws DataNotFoundInDatabase {
        return trelloService.createBoardForDoctor(doctorInfoDto);
    }

    @PostMapping(value = "createCardForOrder")
    public CreatedTrelloCardDto createCardForOrder(@RequestBody OrderInfoWithDoctorDto orderInfoWithDoctorDto)
            throws TrelloException, DataNotFoundInDatabase {
        return trelloService.createCardForOrder(orderInfoWithDoctorDto);
    }

    @PutMapping(value = "changeStatusOfOrder")
    public TrelloCardDto changeStatusOfOrder(@RequestParam Long orderId, @RequestParam ResultStatus resultStatus) throws TrelloException {
        return trelloService.changeStatusOfOrder(orderId, resultStatus);
    }

    @PutMapping(value = "updateCardDescription")
    public TrelloCardDto changeStatusOfOrder(@RequestBody OrderInfoDto orderInfoDto) throws TrelloCardNotFoundException {
        return trelloService.updateTrelloCardDescription(orderInfoDto);
    }

    @DeleteMapping(value = "deleteCardWithOrder")
    public void deleteCardWithOrder(@RequestParam Long orderId) throws TrelloCardNotFoundException {
        trelloService.deleteCardWithOrder(orderId);
    }

    @DeleteMapping(value = "deleteBoardForDoctor")
    public void deleteBoardForDoctor(@RequestParam Long doctorId) throws TrelloBoardNotFoundException {
        trelloService.deleteBoardForDoctor(doctorId);
    }
}
