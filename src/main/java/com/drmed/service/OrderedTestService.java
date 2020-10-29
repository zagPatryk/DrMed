package com.drmed.service;

import com.drmed.domain.additional.ResultStatus;
import com.drmed.domain.order.Order;
import com.drmed.domain.ordered.OrderedTest;
import com.drmed.exceptions.OrderNotFoundException;
import com.drmed.exceptions.OrderedTestNotFoundException;
import com.drmed.repository.OrderRepository;
import com.drmed.repository.OrderedTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedTestService {
    @Autowired
    private OrderedTestRepository orderedTestRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderedTest> getAllOrderedTestFromOrder(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new).getOrderedTests();
    }

    public OrderedTest getOrderedTestById(Long orderedTestId) throws OrderedTestNotFoundException {
        return orderedTestRepository.findById(orderedTestId).orElseThrow(OrderedTestNotFoundException::new);
    }

    public OrderedTest resultOrderedTest(Long orderedTestId, String results)
            throws OrderedTestNotFoundException, OrderNotFoundException {
        OrderedTest orderedTest = orderedTestRepository.findById(orderedTestId).orElseThrow(OrderedTestNotFoundException::new);
        if (orderedTest.getTestResultStatus() == ResultStatus.FINISHED) {
            orderedTest.setResults(results + " previously reported: " + orderedTest.getResults());
            orderedTest.setTestResultStatus(ResultStatus.CORRECTED);
        } else {
            orderedTest.setResults(results);
            orderedTest.setTestResultStatus(ResultStatus.FINISHED);
        }
        Order order = orderRepository.findById(orderedTest.getOrder().getId()).orElseThrow(OrderNotFoundException::new);
        order.updateStatus();
        orderRepository.save(order);
        return orderedTestRepository.save(orderedTest);
    }

    public OrderedTest cancelOrderedTest(Long orderedTestId) {
        return new OrderedTest();
    }
}

