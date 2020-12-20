package com.drmed.base.orderedTest.controller;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.orderedTest.dto.OrderedTestDto;
import com.drmed.base.orderedTest.service.OrderedTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orderedTest")
public class OrderedTestController {
    @Autowired
    private OrderedTestService orderedTestService;

    @GetMapping(value = "getAllOrderedTestFromOrder")
    public List<OrderedTestDto> getAllOrderedTestFromOrder(@RequestParam Long orderId) {
        return orderedTestService.getAllOrderedTestsFromOrder(orderId);
    }

    @GetMapping(value = "getOrderedTestById")
    public OrderedTestDto getOrderedTestById(@RequestParam Long orderedTestId)  throws DataNotFoundInDatabase {
        return orderedTestService.getOrderedTestDtoById(orderedTestId);
    }

    @PostMapping(value = "addOrderedTestToOrder")
    public OrderedTestDto addOrderedTestToOrder(@RequestParam Long orderId, @RequestParam Long testId) throws DataNotFoundInDatabase {
        return orderedTestService.addOrderedTestToOrder(orderId, testId);
    }

    @PostMapping(value = "addManyOrderedTestsToOrder")
    public List<OrderedTestDto> addManyOrderedTestsToOrder(@RequestParam Long orderId, @RequestBody List<Long> testIdsList) throws DataNotFoundInDatabase {
        return orderedTestService.addManyOrderedTestsToOrder(orderId, testIdsList);
    }

    @PutMapping(value = "resultOrderedTest")
    public OrderedTestDto resultOrderedTest(@RequestParam Long orderedTestId, @RequestParam String results) throws DataNotFoundInDatabase {
        return orderedTestService.resultOrderedTest(orderedTestId, results);
    }

    @PutMapping(value = "cancelOrderedTest")
    public OrderedTestDto cancelOrderedTest(@RequestParam Long orderedTestId) throws DataNotFoundInDatabase {
        return orderedTestService.cancelOrderedTest(orderedTestId);
    }
}



