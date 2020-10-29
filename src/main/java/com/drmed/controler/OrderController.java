package com.drmed.controler;

import com.drmed.exceptions.OrderNotFoundException;
import com.drmed.exceptions.TestNotFoundException;
import com.drmed.domain.order.OrderDto;
import com.drmed.mapper.OrderMapper;
import com.drmed.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getOrderById")
    public OrderDto getOrderById(@RequestParam Long orderId) {
        return orderMapper.mapToOrderDto(orderService.findOrderById(orderId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllPatientOrders")
    public OrderDto getAllPatientOrders(@RequestParam Long patientId) {
        return orderMapper.mapToOrderDto(orderService.findOrderById(patientId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addTestToOrder")
    public OrderDto addTestToOrder(@RequestParam Long testId, @RequestParam Long orderId) throws TestNotFoundException {
        return orderMapper.mapToOrderDto(orderService.addTestToOrder(testId, orderId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "cancelOrder")
    public OrderDto cancelOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.cancelOrder(orderId));
    }
}
