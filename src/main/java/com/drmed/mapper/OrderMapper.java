package com.drmed.mapper;

import com.drmed.exceptions.TestNotFoundException;
import com.drmed.domain.order.Order;
import com.drmed.domain.order.OrderDto;
import com.drmed.domain.ordered.OrderedTest;
import com.drmed.domain.ordered.OrderedTestDto;
import com.drmed.repository.OrderRepository;
import com.drmed.repository.OrderedTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderedTestRepository orderedTestRepository;
    @Autowired
    private OrderedTestMapper orderedTestMapper;
    @Autowired
    private PatientMapper patientMapper;

    public Order mapToOrder(OrderDto orderDto) throws TestNotFoundException {
        Order order = new Order();
        if (orderDto.getId() != null) {
            Optional<Order> orderFromDb = orderRepository.findById(order.getId());
            if (orderFromDb.isPresent()) {
                order = orderFromDb.get();
            }
        }
        order.setCode(orderDto.getCode());
        patientMapper.mapToPatient(orderDto.getPatient());
        List<OrderedTest> orderedTestList = new ArrayList<>();
        for (OrderedTestDto orderedTestDto : orderDto.getOrderedTests()) {
            orderedTestList.add(orderedTestMapper.mapToOrderedTest(orderedTestDto));
        }
        order.setOrderedTests(orderedTestList);
        order.setOrderResultStatus(orderDto.getOrderResultStatus());
        return order;
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCode(),
                patientMapper.mapToPatientDto(order.getPatient()),
                orderedTestMapper.mapToOrderedTestDtoList(order.getOrderedTests()),
                order.getOrderResultStatus()
        );
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream().map(this::mapToOrderDto).collect(Collectors.toList());
    }
}
