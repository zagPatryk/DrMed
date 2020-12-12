package com.drmed.orderedTest.repository;

import com.drmed.additional.exceptions.dataNotFoundInDatabase.OrderedTestNotFoundException;
import com.drmed.orderedTest.domain.OrderedTest;
import com.drmed.orderedTest.mapper.OrderedTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderedTestRepository {
    @Autowired
    private OrderedTestCrudRepository orderedTestCrudRepository;
    @Autowired
    private OrderedTestMapper orderedTestMapper;

    public OrderedTest getOrderedTestById(Long id) throws OrderedTestNotFoundException {
        return orderedTestMapper.mapToOrderedTest(
                orderedTestCrudRepository.findById(id).orElseThrow(OrderedTestNotFoundException::new)
        );
    }

    public List<OrderedTest> getAllOrderedTestFromOrder(Long orderId) {
        List<OrderedTestHibernate> orderedTestHibernateList = new ArrayList<>();
        orderedTestCrudRepository.findAllByOrder_Id(orderId).forEach(orderedTestHibernateList::add);
        return orderedTestMapper.mapToOrderedTestList(orderedTestHibernateList);
    }

    public OrderedTest saveOrderedTest(OrderedTest orderedTest) {
        OrderedTestHibernate orderedTestHibernate = orderedTestMapper.mapToOrderedTestHibernate(orderedTest);
        orderedTestCrudRepository.save(orderedTestHibernate);
        return orderedTestMapper.mapToOrderedTest(orderedTestHibernate);
    }
}