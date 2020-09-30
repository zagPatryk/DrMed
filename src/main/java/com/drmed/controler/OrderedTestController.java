package com.drmed.controler;

import com.drmed.domain.exceptions.OrderNotFoundException;
import com.drmed.domain.exceptions.OrderedTestNotFound;
import com.drmed.domain.ordered.OrderedTestDto;
import com.drmed.mapper.OrderedTestMapper;
import com.drmed.service.OrderedTestService;
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
    @Autowired
    private OrderedTestMapper orderedTestMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllOrderedTestFromOrder")
    public List<OrderedTestDto> getAllOrderedTestFromOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        return orderedTestMapper.mapToOrderedTestDtoList(orderedTestService.getAllOrderedTestFromOrder(orderId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrderedTestById")
    public OrderedTestDto getOrderedTestById(@RequestParam Long orderedTestId) throws OrderedTestNotFound {
        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.getOrderedTestById(orderedTestId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "resultOrderedTest")
    public OrderedTestDto resultOrderedTest(@RequestParam Long orderedTestId, @RequestParam String results)
            throws OrderNotFoundException, OrderedTestNotFound {
        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.resultOrderedTest(orderedTestId, results));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "cancelOrderedTest")
    public OrderedTestDto cancelOrderedTest(@RequestParam Long orderedTestId) {
        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.cancelOrderedTest(orderedTestId));
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "deleteOrderedTest")
//    public OrderDto deleteOrderedTest(@RequestParam Long orderId) {
//        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.deleteOrderedTest(orderId));
//    }
}
