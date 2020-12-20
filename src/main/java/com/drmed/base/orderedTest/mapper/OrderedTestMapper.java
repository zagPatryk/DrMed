package com.drmed.base.orderedTest.mapper;

import com.drmed.base.order.mapper.OrderMapper;
import com.drmed.base.orderedTest.domain.OrderedTest;
import com.drmed.base.orderedTest.dto.OrderedTestDto;
import com.drmed.base.orderedTest.dto.OrderedTestInfoDto;
import com.drmed.base.orderedTest.repository.OrderedTestHibernate;
import com.drmed.base.test.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderedTestMapper {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private OrderMapper orderMapper;

    public OrderedTest mapToOrderedTest(OrderedTestHibernate orderedTestHibernate) {
        return orderedTestHibernate == null ? null : new OrderedTest(
                orderedTestHibernate.getId(),
                orderedTestHibernate.getOrder() == null ? null : orderedTestHibernate.getOrder().getId(),
                testMapper.mapToTest(orderedTestHibernate.getTest()),
                orderedTestHibernate.getTestResultStatus(),
                orderedTestHibernate.getResults()
        );
    }

//    public OrderedTest mapToOrderedTest(OrderedTestDto orderedTestDto) {
//        return new OrderedTest(
//                orderedTestDto.getId(),
//                orderedTestDto.getOrderId(),
//                orderedTestDto.getTest().getId(),
//                ResultStatus.TEMPORARY,
//                orderedTestDto.getResults()
//        );
//    }

    public OrderedTestHibernate mapToOrderedTestHibernate(OrderedTest orderedTest) {
        return new OrderedTestHibernate(
                orderedTest.getId(),
                orderMapper.mapToOrderHibernate(orderedTest.getOrder()),
                testMapper.mapToTestHibernate(orderedTest.getTest()),
                orderedTest.getTestResultStatus(),
                orderedTest.getResults()
        );
    }

    public OrderedTestDto mapToOrderedTestDto(OrderedTest orderedTest) {
        return new OrderedTestDto(
                orderedTest.getId(),
                orderedTest.getOrderId() != null
                        ? orderedTest.getOrderId()
                        : orderedTest.getOrder() != null
                            ? orderedTest.getOrder().getId()
                            : null,
                testMapper.mapToTestDto(orderedTest.getTest()),
                orderedTest.getResults()
        );
    }

    public OrderedTestInfoDto mapToOrderedTestInfoDto(OrderedTest orderedTest) {
        return new OrderedTestInfoDto(
                orderedTest.getId(),
                testMapper.mapToTestInfoDto(orderedTest.getTest())
        );
    }

    public List<OrderedTest> mapToOrderedTestList(List<OrderedTestHibernate> orderedTestHibernateList) {
        return orderedTestHibernateList == null ? new ArrayList<>()
                : orderedTestHibernateList.stream().map(this::mapToOrderedTest).collect(Collectors.toList());
    }

//    public List<OrderedTest> mapToOrderedTestListFromDto(List<OrderedTestDto> orderedTestDtoList) {
//        return orderedTestDtoList == null ? new ArrayList<>()
//                : orderedTestDtoList.stream().map(this::mapToOrderedTest).collect(Collectors.toList());
//    }

    public List<OrderedTestHibernate> mapToOrderedTestHibernateList(List<OrderedTest> orderedTestList) {
        return orderedTestList == null ? new ArrayList<>()
                : orderedTestList.stream().map(this::mapToOrderedTestHibernate).collect(Collectors.toList());
    }

    public List<OrderedTestDto> mapToOrderedTestDtoList(List<OrderedTest> orderedTestList) {
        return orderedTestList == null ? new ArrayList<>()
                : orderedTestList.stream().map(this::mapToOrderedTestDto).collect(Collectors.toList());
    }

    public List<OrderedTestInfoDto> mapToOrderedTestInfoDtoList(List<OrderedTest> orderedTestList) {
        return orderedTestList == null ? new ArrayList<>()
                : orderedTestList.stream().map(this::mapToOrderedTestInfoDto).collect(Collectors.toList());
    }
}
