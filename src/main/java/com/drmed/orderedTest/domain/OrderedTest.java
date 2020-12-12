package com.drmed.orderedTest.domain;

import com.drmed.additional.statuses.ResultStatus;
import com.drmed.order.domain.Order;
import com.drmed.test.domain.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderedTest {
    private Long id;

    private Long orderId;
    private Order order;

    private Long testId;
    private Test test;

    private ResultStatus testResultStatus = ResultStatus.PENDING;
    private String results;

    public OrderedTest(Long id, Long orderId, Long testId, ResultStatus testResultStatus, String results) {
        this.id = id;
        this.orderId = orderId;
        this.testId = testId;
        this.testResultStatus = testResultStatus;
        this.results = results;
    }


}
