package com.drmed.base.order.mapper;

import com.drmed.base.order.domain.Order;
import com.drmed.base.order.dto.OrderDto;
import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.base.order.dto.OrderInfoTrelloDto;
import com.drmed.base.order.repository.OrderHibernate;
import com.drmed.base.orderedTest.mapper.OrderedTestMapper;
import com.drmed.base.visit.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Autowired
    private OrderedTestMapper orderedTestMapper;
    @Autowired
    private VisitMapper visitMapper;

    public OrderHibernate mapToOrderHibernateList(Order order) {
        return new OrderHibernate(
                order.getId(),
                order.getCode(),
                visitMapper.mapToVisitHibernate(order.getVisit()),
                orderedTestMapper.mapToOrderedTestHibernateList(order.getOrderedTests()),
                order.getOrderResultStatus(),
                ""
        );
    }

    public Order mapToOrder(OrderHibernate orderHibernate) {
        return new Order(
                orderHibernate.getId(),
                orderHibernate.getCode(),
                visitMapper.mapToVisit(orderHibernate.getVisit()),
                orderedTestMapper.mapToOrderedTestList(orderHibernate.getOrderedTests()),
                orderHibernate.getOrderResultStatus(),
                orderHibernate.getTrelloOrderCardId()
        );
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCode(),
                visitMapper.mapToVisitInfoDto(order.getVisit()),
                orderedTestMapper.mapToOrderedTestDtoList(order.getOrderedTests())
        );
    }

//    public Order mapToOrder(OrderDto orderDto) {
//        return new Order(
//                orderDto.getId(),
//                orderDto.getCode(),
//                visitMapper.mapToVisit(orderDto.getVisit()),
//                orderedTestMapper.mapToOrderedTestListFromDto(orderDto.getOrderedTests())
//        );
//    }

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

    public List<OrderHibernate> mapToOrderHibernateList(List<Order> orderList) {
        return orderList.stream().map(this::mapToOrderHibernateList).collect(Collectors.toList());
    }

    public List<OrderInfoDto> mapToOrderInfoDtoList(List<Order> orderList) {
        return orderList.stream().map(this::mapToOrderInfoDto).collect(Collectors.toList());
    }
}
