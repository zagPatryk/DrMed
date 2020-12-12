package com.drmed.order.repository;

import com.drmed.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.order.domain.Order;
import com.drmed.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepository {
    @Autowired
    private OrderCrudRepository orderCrudRepository;
    @Autowired
    private OrderMapper orderMapper;

    public Order getOrderById(Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrder(orderCrudRepository.findById(orderId).orElseThrow(OrderNotFoundException::new));
    }

    public List<Order> getAllOrdersForPatient(Long patientId) {
        List<OrderHibernate> orderHibernateList = new ArrayList<>();
        orderCrudRepository.findAllByPatient_Id(patientId).forEach(orderHibernateList::add);
        return orderMapper.mapToOrderList(orderHibernateList);
    }

    public List<Order> getAllByCodeContains(Integer code) {
        List<OrderHibernate> orderHibernateList = new ArrayList<>();
        orderCrudRepository.findAllByCodeContains(code).forEach(orderHibernateList::add);
        return orderMapper.mapToOrderList(orderHibernateList);
    }

    public Order saveOrder(Order order) {
        OrderHibernate orderHibernate = orderMapper.mapToOrderHibernate(order);
        orderCrudRepository.save(orderHibernate);
        return orderMapper.mapToOrder(orderHibernate);
    }
}