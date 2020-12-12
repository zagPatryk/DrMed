package com.drmed.order.service;

import com.drmed.additional.exceptions.dataNotFoundInDatabase.OrderedTestNotFoundException;
import com.drmed.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.additional.statuses.ResultStatus;
import com.drmed.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.order.domain.Order;
import com.drmed.order.dto.NewOrderDto;
import com.drmed.order.dto.OrderDto;
import com.drmed.order.mapper.OrderMapper;
import com.drmed.order.repository.OrderRepository;
import com.drmed.orderedTest.domain.OrderedTest;
import com.drmed.orderedTest.service.OrderedTestService;
import com.drmed.patient.service.PatientService;
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
    private PatientService patientService;

    public OrderDto getOrderDtoById(Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderRepository.getOrderById(orderId));
    }

    public Order getOrderById(Long orderId) throws OrderNotFoundException {
        return orderRepository.getOrderById(orderId);
    }

    public List<OrderDto> getAllOrdersFromPatient(Long patientId) {
        return orderMapper.mapToOrderDtoList(orderRepository.getAllOrdersForPatient(patientId));
    }

    public Order saveOrder(Order order) throws OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException {
        Order temporaryOrder;
        if(order.getId() != null) {
            temporaryOrder = orderRepository.getOrderById(order.getId());
        } else {
            temporaryOrder = new Order();
        }
        temporaryOrder.setCode(order.getCode());
        temporaryOrder.setPatient(order.getPatient());
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
            throws OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException, PatientNotFoundException {
        Order order = new Order();
        order.setCode(newOrderDto.getCode());
        order.setPatient(patientService.getPatientById(newOrderDto.getPatientId()));
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