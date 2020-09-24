package com.drmed.service;

import com.drmed.domain.order.Order;
import com.drmed.domain.ordered.OrderedTest;
import com.drmed.domain.test.Test;
import com.drmed.repository.OrderRepository;
import com.drmed.repository.OrderedTestRepository;
import com.drmed.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Order addTestToOrder(Long testId, Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        Test test = testRepository.findById(testId).get();

        OrderedTest orderedTest = new OrderedTest(order, test);
        order.getOrderedTests().add(orderedTest);
        test.getOrderedTest().add(orderedTest);
        orderRepository.save(order);
        testRepository.save(test);
        orderedTestRepository.save(orderedTest);
        return order;
    }
}
