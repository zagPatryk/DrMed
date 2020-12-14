package com.drmed.base.order.service;

import com.drmed.base.additional.exceptions.VisitNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderedTestNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.order.domain.Order;
import com.drmed.base.order.dto.NewOrderDto;
import com.drmed.base.order.dto.OrderDto;
import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.base.order.mapper.OrderMapper;
import com.drmed.base.order.repository.OrderRepository;
import com.drmed.base.orderedTest.domain.OrderedTest;
import com.drmed.base.orderedTest.service.OrderedTestService;
import com.drmed.base.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderedTestService orderedTestService;
    @Autowired
    private VisitService visitService;

    public OrderDto getOrderDtoById(Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderRepository.getOrderById(orderId));
    }

    public Order getOrderById(Long orderId) throws OrderNotFoundException {
        return orderRepository.getOrderById(orderId);
    }

    public List<OrderInfoDto> getAllOrdersFromVisit(Long visitId) {
        return orderMapper.mapToOrderInfoDtoList(orderRepository.getAllOrdersForVisit(visitId));
    }

    public List<OrderInfoDto> getAllOrdersByCodeContains(String code) {
        return orderMapper.mapToOrderInfoDtoList(orderRepository.getAllByCodeContains(code));
    }

    public Order saveOrder(Order order) throws OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException {
        Order temporaryOrder;
        if(order.getId() != null) {
            temporaryOrder = orderRepository.getOrderById(order.getId());
        } else {
            temporaryOrder = new Order();
        }
        temporaryOrder.setCode(order.getCode());
        temporaryOrder.setVisit(order.getVisit());
        for (OrderedTest orderedTest : order.getOrderedTests()) {
            if(!temporaryOrder.getOrderedTests().contains(orderedTest)) {
                temporaryOrder.getOrderedTests().add(orderedTest);
            }
            orderedTestService.saveOrderedTest(orderedTest);
        }
        temporaryOrder.setOrderResultStatus(order.getOrderResultStatus());
        return orderRepository.saveOrder(temporaryOrder);
    }

    public OrderDto addTestToOrder(Long orderId, List<Long> testIdList) throws OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException {
        Order order = orderRepository.getOrderById(orderId);
        createOrderedTestsForOrder(order, testIdList);
        return orderMapper.mapToOrderDto(saveOrder(order));
    }

    public OrderDto addOrderForPatient(NewOrderDto newOrderDto)
            throws OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException, VisitNotFoundException {
        Order order = new Order();
        order.setCode(newOrderDto.getCode());
        order.setVisit(visitService.getVisitById(newOrderDto.getVisitId()));
        createOrderedTestsForOrder(order, newOrderDto.getTestsIds());
        order.setOrderResultStatus(ResultStatus.PENDING);
        return orderMapper.mapToOrderDto(saveOrder(order));
    }

    private void createOrderedTestsForOrder(Order order, List<Long> testIdList) {
        for (Long testId : testIdList) {
            OrderedTest orderedTest = new OrderedTest();
            orderedTest.setOrderId(order.getId());
            orderedTest.setTestId(testId);
            orderedTest.setTestResultStatus(ResultStatus.PENDING);
            order.getOrderedTests().add(orderedTest);
        }
    }

    public OrderDto cancelOrder(Long orderId) throws OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException {
        Order order = orderRepository.getOrderById(orderId);
        order.setOrderResultStatus(ResultStatus.CANCELLED);
        for (OrderedTest orderedTest : order.getOrderedTests()) {
            orderedTestService.cancelOrderedTest(orderedTest.getId());
        }
        return orderMapper.mapToOrderDto(saveOrder(order));
    }

    public void checkOrderStatus(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.getOrderById(orderId);
        if (order.getOrderedTests().stream().map(OrderedTest::getTestResultStatus)
                .anyMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.PENDING)) {
            order.setOrderResultStatus(ResultStatus.PENDING);
        } else if (order.getOrderedTests().stream().map(OrderedTest::getTestResultStatus)
                .anyMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.CORRECTED)) {
            order.setOrderResultStatus(ResultStatus.CORRECTED);
        } else if (order.getOrderedTests().stream().map(OrderedTest::getTestResultStatus)
                .allMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.CANCELLED)) {
            order.setOrderResultStatus(ResultStatus.CANCELLED);
        }
        orderRepository.saveOrder(order);
    }

}
