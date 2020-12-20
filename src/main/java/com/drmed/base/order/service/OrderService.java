package com.drmed.base.order.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderedTestNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
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

    public OrderDto addOrderForPatient(NewOrderDto newOrderDto) throws VisitNotFoundException {
        Order order = new Order.OrderBuilder()
                .setCode(newOrderDto.getCode())
                .setVisit(visitService.getVisitById(newOrderDto.getVisitId()))
                .setOrderResultStatus(ResultStatus.PENDING)
                .build();
        return orderMapper.mapToOrderDto(orderRepository.saveOrder(order));
    }

    public OrderDto cancelOrder(Long orderId) throws OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException {
        Order order = orderRepository.getOrderById(orderId);
        order.setOrderResultStatus(ResultStatus.CANCELLED);
        for (OrderedTest orderedTest : order.getOrderedTests()) {
            orderedTestService.cancelOrderedTest(orderedTest.getId());
        }
        return orderMapper.mapToOrderDto(orderRepository.saveOrder(order));
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

//    public OrderDto addTestToOrder(Long orderId, List<Long> testIdList) throws OrderNotFoundException {
//        Order order = orderRepository.getOrderById(orderId);
//        System.out.println(testIdList.get(0));
//        System.out.println(testIdList.get(1));
//        createOrderedTestsForOrder(order, testIdList);
//        System.out.println(order.getOrderedTests());
//        return orderMapper.mapToOrderDto(orderRepository.saveOrder(order));

//    }
    // zmienic
//    public Order saveOrder(Order order) throws OrderNotFoundException, OrderedTestNotFoundException, TestNotFoundException {
//        Order temporaryOrder;
//        temporaryOrder = order.getId() != null
//                ? orderRepository.getOrderById(order.getId())
//                : new Order();
//        temporaryOrder.setCode(order.getCode());
//        temporaryOrder.setVisit(order.getVisit());
//        for (OrderedTest orderedTest : order.getOrderedTests()) {
//            if(!temporaryOrder.getOrderedTests().contains(orderedTest)) {
//                temporaryOrder.getOrderedTests().add(orderedTest);
//            }
//            orderedTestService.saveOrderedTest(orderedTest);
//        }
//        temporaryOrder.setOrderResultStatus(order.getOrderResultStatus());
//        return orderRepository.saveOrder(temporaryOrder);

//    }
//    private void createOrderedTestsForOrder(Order order, List<Long> testIdList) {
//        System.out.println(111111);
//        for (Long testId : testIdList) {
//            OrderedTest orderedTest = new OrderedTest();
//            System.out.println(orderedTest);
//            orderedTest.setOrderId(order.getId());
//            System.out.println(orderedTest);
//            orderedTest.setTestId(testId);
//            System.out.println(orderedTest);
//            orderedTest.setTestResultStatus(ResultStatus.PENDING);
//            System.out.println(orderedTest);
//            System.out.println(order.getOrderedTests());
//            order.getOrderedTests().add(orderedTest);
//            System.out.println(order.getOrderedTests());
//        }

//    }
}
