package com.drmed.base.orderedTest.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderedTestNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.order.service.OrderService;
import com.drmed.base.orderedTest.domain.OrderedTest;
import com.drmed.base.orderedTest.dto.OrderedTestDto;
import com.drmed.base.orderedTest.mapper.OrderedTestMapper;
import com.drmed.base.orderedTest.repository.OrderedTestRepository;
import com.drmed.base.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedTestService {
    @Autowired
    private OrderedTestRepository orderedTestRepository;
    @Autowired
    private OrderedTestMapper orderedTestMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TestService testService;

    public List<OrderedTestDto> getAllOrderedTestsFromOrder(Long orderId) {
        return orderedTestMapper.mapToOrderedTestDtoList(orderedTestRepository.getAllOrderedTestFromOrder(orderId));
    }

    public OrderedTestDto getOrderedTestById(Long orderedTestId) throws OrderedTestNotFoundException {
        return orderedTestMapper.mapToOrderedTestDto(orderedTestRepository.getOrderedTestById(orderedTestId));
    }

    public OrderedTestDto addOrderedTestToOrder(Long orderId, Long testId) throws TestNotFoundException, OrderNotFoundException {
        OrderedTest orderedTest = new OrderedTest();
        orderedTest.setOrderId(orderId);
        orderedTest.setTestId(testId);
        mapTestIdToTest(orderedTest);
        mapOrderIdToOrder(orderedTest);
        orderedTest = orderedTestRepository.saveOrderedTest(orderedTest);
        orderService.checkOrderStatus(orderId);
        return orderedTestMapper.mapToOrderedTestDto(orderedTest);
    }

    public List<OrderedTestDto> addManyOrderedTestsToOrder(Long orderId, List<Long> testIdsList) throws TestNotFoundException, OrderNotFoundException {
        List<OrderedTestDto> orderedTestDtoList = new ArrayList<>();
        for (Long testId : testIdsList) {
            orderedTestDtoList.add(addOrderedTestToOrder(orderId, testId));
        }
        return orderedTestDtoList;
    }

    public OrderedTestDto resultOrderedTest(Long orderedTestId, String results) throws OrderedTestNotFoundException, TestNotFoundException, OrderNotFoundException {
        OrderedTest orderedTest = orderedTestRepository.getOrderedTestById(orderedTestId);
        setResultsAndStatus(results, orderedTest);
        mapOrderIdToOrder(orderedTest);
        mapTestIdToTest(orderedTest);
        orderedTestRepository.saveOrderedTest(orderedTest);
        orderService.checkOrderStatus(orderedTest.getOrderId());
        return orderedTestMapper.mapToOrderedTestDto(orderedTest);
    }

    public OrderedTestDto cancelOrderedTest(Long orderedTestId) throws OrderedTestNotFoundException {
        OrderedTest orderedTest = orderedTestRepository.getOrderedTestById(orderedTestId);
        if (orderedTest.getTestResultStatus() == ResultStatus.FINISHED ||
                orderedTest.getTestResultStatus() == ResultStatus.CORRECTED) {
            orderedTest.setResults("Test cancelled. Previously reported as: " + orderedTest.getResults());
        } else {
            orderedTest.setResults("Test cancelled.");
        }
        return orderedTestMapper.mapToOrderedTestDto(orderedTestRepository.saveOrderedTest(orderedTest));
    }

    public void setResultsAndStatus(String results, OrderedTest orderedTest) {
        if (orderedTest.getResults().equals("") && !results.equals("")) {
            orderedTest.setResults(results);
            orderedTest.setTestResultStatus(ResultStatus.FINISHED);
        } else if(!orderedTest.getResults().equals("") && !results.equals("")) {
            orderedTest.setResults(results + ". Previously reported as: " + orderedTest.getResults());
            orderedTest.setTestResultStatus(ResultStatus.CORRECTED);
        } else {
            orderedTest.setTestResultStatus(ResultStatus.PENDING);
        }
    }

    private void mapTestIdToTest(OrderedTest orderedTest) throws TestNotFoundException {
        orderedTest.setTest(testService.getTestById(orderedTest.getTestId()));
    }

    private void mapOrderIdToOrder(OrderedTest orderedTest) throws OrderNotFoundException {
        orderedTest.setOrder(orderService.getOrderById(orderedTest.getOrderId()));
    }

    //    public OrderedTest saveOrderedTest(OrderedTest orderedTest)
//            throws OrderedTestNotFoundException, TestNotFoundException, OrderNotFoundException {
//        OrderedTest orderedTestTemporary;
//        if(orderedTest.getId() != null) {
//            orderedTestTemporary = orderedTestRepository.getOrderedTestById(orderedTest.getId());
//        } else {
//            orderedTestTemporary = new OrderedTest();
//        }
//        orderedTestTemporary.setOrderId(orderedTest.getOrderId());
//
//        if(orderedTest.getOrder() != null) {
//            orderedTestTemporary.setOrder(orderedTest.getOrder());
//        } else {
//            mapOrderIdToOrder(orderedTestTemporary);
//        }
//
//        if(orderedTest.getTest() != null) {
//            orderedTestTemporary.setTest(orderedTest.getTest());
//        } else {
//            mapTestIdToTest(orderedTestTemporary);
//        }
//        orderedTestTemporary.setResults(orderedTest.getResults());
//        orderedTestTemporary.setTestResultStatus(orderedTest.getTestResultStatus());
//        return orderedTestRepository.saveOrderedTest(orderedTestTemporary);
//    }
}

