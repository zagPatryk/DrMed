package com.drmed.service;

import com.drmed.domain.additional.ResultStatus;
import com.drmed.exceptions.OrderNotFoundException;
import com.drmed.exceptions.TestNotFoundException;
import com.drmed.domain.order.Order;
import com.drmed.domain.ordered.OrderedTest;
import com.drmed.domain.test.Test;
import com.drmed.repository.OrderRepository;
import com.drmed.repository.OrderedTestRepository;
import com.drmed.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private OrderedTestRepository orderedTestRepository;

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseGet(Order::new);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findAllPatientOrders(Long id) {
        return orderRepository.findByPatientId(id);
    }

    public Order addTestToOrder(Long testId, Long orderId) throws TestNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order order = optionalOrder.orElseGet(Order::new);

        Optional<Test> optionalTest = testRepository.findById(testId);
        Test test = optionalTest.orElseThrow(TestNotFoundException::new);

        OrderedTest orderedTest = new OrderedTest(order, test);
        order.getOrderedTests().add(orderedTest);
        test.getOrderedTest().add(orderedTest);
        orderRepository.save(order);
        testRepository.save(test);
        orderedTestRepository.save(orderedTest);
        return order;
    }

    public Order cancelOrder(Long orderId) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            optionalOrder.get().setOrderResultStatus(ResultStatus.CANCELLED);
            for (OrderedTest orderedTest : optionalOrder.get().getOrderedTests()) {
                if (orderedTest.getTestResultStatus() == ResultStatus.PENDING) {
                    orderedTest.setResults(orderedTest.getResults() + " canceled by user");
                    orderedTest.setTestResultStatus(ResultStatus.CANCELLED);
                }
                orderedTestRepository.save(orderedTest);
            }
            orderRepository.save(optionalOrder.get());
        } else {
            throw new OrderNotFoundException();
        }
        return optionalOrder.get();
    }
}
