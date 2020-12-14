package com.drmed.base.order.domain;

import com.drmed.base.additional.statuses.ResultStatus;
import com.drmed.base.orderedTest.domain.OrderedTest;
import com.drmed.base.visit.domain.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Long id;
    private String code;
    private Visit visit;
    private List<OrderedTest> orderedTests = new ArrayList<>();
    private ResultStatus orderResultStatus;
    private String trelloOrderCardId;

    public Order(Long id, String code, Visit visit, List<OrderedTest> orderedTests, ResultStatus orderResultStatus,
                 String trelloOrderCardId) {
        this.id = id;
        this.code = code;
        this.visit = visit;
        this.orderedTests = orderedTests;
        this.orderResultStatus = orderResultStatus;
        this.trelloOrderCardId = trelloOrderCardId;
    }

    public Order(Long id, String code, Visit visit, List<OrderedTest> orderedTests) {
        this.id = id;
        this.code = code;
        this.visit = visit;
        this.orderedTests = orderedTests;
    }
}
