package com.drmed.order.domain;

import com.drmed.additional.statuses.ResultStatus;
import com.drmed.orderedTest.domain.OrderedTest;
import com.drmed.patient.domain.Patient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Long id;
    private Integer code;
    private Patient patient;
    private List<OrderedTest> orderedTests = new ArrayList<>();
    private ResultStatus orderResultStatus;

    public Order(Long id, Integer code, Patient patient, List<OrderedTest> orderedTests, ResultStatus orderResultStatus) {
        this.id = id;
        this.code = code;
        this.patient = patient;
        this.orderedTests = orderedTests;
        this.orderResultStatus = orderResultStatus;
    }

//    private ResultStatus updateStatus() {
//        if (orderedTests.stream().map(OrderedTest::getTestResultStatus)
//                .anyMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.PENDING)) {
//            orderResultStatus = ResultStatus.PENDING;
//            return ResultStatus.PENDING;
//        } else if (orderedTests.stream().map(OrderedTest::getTestResultStatus)
//                .anyMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.CORRECTED)) {
//            orderResultStatus = ResultStatus.CORRECTED;
//            return ResultStatus.CORRECTED;
//        } else if (orderedTests.stream().map(OrderedTest::getTestResultStatus)
//                .allMatch(orderedTestStatus -> orderedTestStatus == ResultStatus.CANCELLED)) {
//            orderResultStatus = ResultStatus.CANCELLED;
//            return ResultStatus.CANCELLED;
//        } else {
//            return orderResultStatus;
//        }
//    }
}
