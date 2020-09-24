package com.drmed.mapper;

import com.drmed.domain.additional.Status;
import com.drmed.domain.exceptions.TestNotFoundException;
import com.drmed.domain.order.Order;
import com.drmed.domain.ordered.OrderedTest;
import com.drmed.domain.ordered.OrderedTestDto;
import com.drmed.domain.test.Test;
import com.drmed.repository.OrderRepository;
import com.drmed.repository.OrderedTestRepository;
import com.drmed.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderedTestMapper {

    @Autowired
    private OrderedTestRepository orderedTestRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestMapper testMapper;

    public OrderedTest mapToOrderedTest(OrderedTestDto orderedTestDto) throws TestNotFoundException {
        OrderedTest orderedTest = new OrderedTest();
        if (orderedTestDto.getId() != null) {
            Optional<OrderedTest> orderedTestFromDb = orderedTestRepository.findById(orderedTest.getId());
            if (orderedTestFromDb.isPresent()) {
                orderedTest = orderedTestFromDb.get();
            }
        }
        Optional<Order> order = orderRepository.findById(orderedTestDto.getOrderId());
        if (order.isPresent()) {
            orderedTest.setOrder(order.get());
        } else {
            orderedTest.setOrder(new Order());
        }
        orderedTest.setResults(orderedTestDto.getResults());
        Optional<Test> test = testRepository.findById(orderedTestDto.getTest().getId());
        if (test.isPresent()) {
            orderedTest.setTest(test.get());
        } else {
            throw new TestNotFoundException();
        }
        if (orderedTestDto.getTestStatus() != null) {
            orderedTest.setTestStatus(orderedTestDto.getTestStatus());
        } else {
            orderedTest.setTestStatus(Status.PENDING);
        }
        return orderedTest;
    }

    public OrderedTestDto mapToOrderedTestDto(OrderedTest orderedTest) {
        return new OrderedTestDto(
                orderedTest.getId(),
                orderedTest.getOrder().getId(),
                testMapper.mapToTestDto(orderedTest.getTest()),
                orderedTest.getTestStatus(),
                orderedTest.getResults()
        );
    }

    public List<OrderedTestDto> mapToOrderedTestDtoList(List<OrderedTest> orderedTestList) {
        return orderedTestList.stream()
                .map(this::mapToOrderedTestDto)
                .collect(Collectors.toList());
    }

}
