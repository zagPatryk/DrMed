package com.drmed.base.order.mapper;

import com.drmed.base.order.domain.Order;
import com.drmed.base.order.dto.OrderDto;
import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.base.order.repository.OrderHibernate;
import com.drmed.base.orderedTest.mapper.OrderedTestMapper;
import com.drmed.base.visit.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Autowired
    private OrderedTestMapper orderedTestMapper;
    @Autowired
    private VisitMapper visitMapper;

    public OrderHibernate mapToOrderHibernate(Order order) {
        return order == null ? null : new OrderHibernate(
                order.getId(),
                order.getCode(),
                visitMapper.mapToVisitHibernate(order.getVisit()),
                orderedTestMapper.mapToOrderedTestHibernateList(order.getOrderedTests()),
                order.getOrderResultStatus(),
                null
        );
    }

    public Order mapToOrder(OrderHibernate orderHibernate) {
        return new Order(
                orderHibernate.getId(),
                orderHibernate.getCode(),
                visitMapper.mapToVisit(orderHibernate.getVisit()),
                orderedTestMapper.mapToOrderedTestList(orderHibernate.getOrderedTests()),
                orderHibernate.getOrderResultStatus()
        );
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCode(),
                visitMapper.mapToVisitInfoDto(order.getVisit()),
                order.getOrderedTests().isEmpty() ? new ArrayList<>()
                        : orderedTestMapper.mapToOrderedTestDtoList(order.getOrderedTests())
        );
    }

    public OrderInfoDto mapToOrderInfoDto(Order order) {
        return new OrderInfoDto(
                order.getId(),
                order.getCode(),
                orderedTestMapper.mapToOrderedTestInfoDtoList(order.getOrderedTests())
        );
    }

    public List<Order> mapToOrderList(List<OrderHibernate> orderHibernateList) {
        return orderHibernateList == null ? new ArrayList<>()
                : orderHibernateList.stream().map(this::mapToOrder).collect(Collectors.toList());
    }

    public List<OrderHibernate> mapToOrderHibernate(List<Order> orderList) {
        return orderList == null ? new ArrayList<>()
                : orderList.stream().map(this::mapToOrderHibernate).collect(Collectors.toList());
    }

    public List<OrderInfoDto> mapToOrderInfoDtoList(List<Order> orderList) {
        return orderList == null ? new ArrayList<>()
                : orderList.stream().map(this::mapToOrderInfoDto).collect(Collectors.toList());
    }
}
