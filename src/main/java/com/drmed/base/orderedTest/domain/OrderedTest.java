package com.drmed.base.orderedTest.domain;

import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.order.domain.Order;
import com.drmed.base.test.domain.Test;
import lombok.*;

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

    public OrderedTest(Long id, Long orderId, Test test, ResultStatus testResultStatus, String results) {
        this.id = id;
        this.orderId = orderId;
        this.test = test;
        this.testResultStatus = testResultStatus;
        this.results = results;
    }
}
