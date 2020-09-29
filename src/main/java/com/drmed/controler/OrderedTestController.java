package com.drmed.controler;

import com.drmed.mapper.OrderedTestMapper;
import com.drmed.service.OrderedTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orderedTest")
public class OrderedTestController {

    @Autowired
    private OrderedTestService orderedTestService;
    @Autowired
    private OrderedTestMapper orderedTestMapper;

//    @RequestMapping(method = RequestMethod.GET, value = "getAllOrderedTestFromOrder")
//    public OrderDto getAllOrderedTestFromOrder(@RequestParam Long orderId) {
//        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.findOrderById(orderId));
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "getOrderedTestById")
//    public OrderDto getOrderedTestById(@RequestParam Long orderId) {
//        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.findOrderById(orderId));
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "addTestToOrder")
//    public OrderDto addTestToOrder(@RequestParam Long orderId) {
//        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.findOrderById(orderId));
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "resultOrderedTest")
//    public OrderDto resultOrderedTest(@RequestParam Long orderId) {
//        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.findOrderById(orderId));
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "cancelOrderedTest")
//    public OrderDto cancelOrderedTest(@RequestParam Long orderId) {
//        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.findOrderById(orderId));
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "deleteOrderedTest")
//    public OrderDto deleteOrderedTest(@RequestParam Long orderId) {
//        return orderedTestMapper.mapToOrderedTestDto(orderedTestService.deleteOrderedTest(orderId));
//    }
}
