package com.drmed.order.mapper;

import com.drmed.additional.statuses.ResultStatus;
import com.drmed.order.domain.Order;
import com.drmed.order.dto.OrderDto;
import com.drmed.order.dto.OrderInfoDto;
import com.drmed.order.dto.OrderInfoTrelloDto;
import com.drmed.order.repository.OrderHibernate;
import com.drmed.orderedTest.mapper.OrderedTestMapper;
import com.drmed.patient.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Autowired
    private OrderedTestMapper orderedTestMapper;
    @Autowired
    private PatientMapper patientMapper;

    public OrderHibernate mapToOrderHibernate(Order order) {
        return new OrderHibernate(
                order.getId(),
                order.getCode(),
                patientMapper.mapToPatientHibernate(order.getPatient()),
                orderedTestMapper.mapToOrderedTestHibernateList(order.getOrderedTests()),
                order.getOrderResultStatus(),
                ""
        );
    }

    public Order mapToOrder(OrderHibernate orderHibernate) {
        return new Order(
                orderHibernate.getId(),
                orderHibernate.getCode(),
                patientMapper.mapToPatient(orderHibernate.getPatient()),
                orderedTestMapper.mapToOrderedTestList(orderHibernate.getOrderedTests()),
                orderHibernate.getOrderResultStatus(),
                orderHibernate.getTrelloOrderCardId()
        );
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCode(),
                patientMapper.mapToPatientDto(order.getPatient()),
                orderedTestMapper.mapToOrderedTestDtoList(order.getOrderedTests())
        );
    }

    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getCode(),
                patientMapper.mapToPatient(orderDto.getPatient()),
                orderedTestMapper.mapToOrderedTestListFromDto(orderDto.getOrderedTests()),
                ResultStatus.TEMPORARY,
                ""
        );
    }

    public OrderInfoDto mapToOrderInfoDto(Order order) {
        return new OrderInfoDto(
                order.getId(),
                order.getCode(),
                orderedTestMapper.mapToOrderedTestInfoDtoList(order.getOrderedTests())
        );
    }

    public OrderInfoTrelloDto mapToOrderInfoTrelloDto(Order order) {
        return new OrderInfoTrelloDto(
                order.getCode(),
                orderedTestMapper.mapToOrderedTestInfoDtoList(order.getOrderedTests()),
                order.getTrelloOrderCardId()
        );
    }

    public List<Order> mapToOrderList(List<OrderHibernate> orderHibernateList) {
        return orderHibernateList.stream().map(this::mapToOrder).collect(Collectors.toList());
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream().map(this::mapToOrderDto).collect(Collectors.toList());
    }

    public List<OrderHibernate> mapToOrderHibernate(List<Order> orderList) {
        return orderList.stream().map(this::mapToOrderHibernate).collect(Collectors.toList());
    }

    public List<OrderInfoDto> mapToOrderInfoDtoList(List<Order> orderList) {
        return orderList.stream().map(this::mapToOrderInfoDto).collect(Collectors.toList());
    }

    //    public Order mapToOrder(OrderDto orderDto) throws TestNotFoundException {
//        Order order = new Order();
//        if (orderDto.getId() != null) {
//            Optional<Order> orderFromDb = orderCrudRepository.findById(order.getId());
//            if (orderFromDb.isPresent()) {
//                order = orderFromDb.get();
//            }
//        }
//        order.setCode(orderDto.getCode());
//        patientMapper.mapToPatient(orderDto.getPatient());
//        List<OrderedTest> orderedTestList = new ArrayList<>();
//        for (OrderedTestDto orderedTestDto : orderDto.getOrderedTests()) {
//            orderedTestList.add(orderedTestMapper.mapToOrderedTest(orderedTestDto));
//        }
//        order.setOrderedTests(orderedTestList);
//        order.setOrderResultStatus(orderDto.getOrderResultStatus());
//        return order;
//    }
//
//    public OrderDto mapToOrderDto(Order order) {
//        return new OrderDto(
//                order.getId(),
//                order.getCode(),
//                patientMapper.mapToPatientDto(order.getPatient()),
//                orderedTestMapper.mapToOrderedTestDtoList(order.getOrderedTests()),
//                order.getOrderResultStatus()
//        );
//    }
//
//    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
//        return orderList.stream().map(this::mapToOrderDto).collect(Collectors.toList());
//    }

}
