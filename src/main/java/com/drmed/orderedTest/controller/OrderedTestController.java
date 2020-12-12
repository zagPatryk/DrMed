package com.drmed.orderedTest.controller;

import com.drmed.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.orderedTest.dto.OrderedTestDto;
import com.drmed.orderedTest.service.OrderedTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/orderedTest")
public class OrderedTestController {
    @Autowired
    private OrderedTestService orderedTestService;

    @RequestMapping(method = RequestMethod.GET, value = "getAllOrderedTestFromOrder")
    public List<OrderedTestDto> getAllOrderedTestFromOrder(@RequestParam Long orderId) {
        return orderedTestService.getAllOrderedTestsFromOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrderedTestById")
    public OrderedTestDto getOrderedTestById(@RequestParam Long orderedTestId)  throws DataNotFoundInDatabase {
        return orderedTestService.getOrderedTestById(orderedTestId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "resultOrderedTest")
    public OrderedTestDto resultOrderedTest(@RequestParam Long orderedTestId, @RequestParam String results) throws DataNotFoundInDatabase {
        return orderedTestService.resultOrderedTest(orderedTestId, results);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "cancelOrderedTest")
    public OrderedTestDto cancelOrderedTest(@RequestParam Long orderedTestId) throws DataNotFoundInDatabase {
        return orderedTestService.cancelOrderedTest(orderedTestId);
    }
}



