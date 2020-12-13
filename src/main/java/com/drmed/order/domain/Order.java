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
    private String trelloOrderCardId;

    public Order(Long id, Integer code, Patient patient, List<OrderedTest> orderedTests, ResultStatus orderResultStatus,
                 String trelloOrderCardId) {
        this.id = id;
        this.code = code;
        this.patient = patient;
        this.orderedTests = orderedTests;
        this.orderResultStatus = orderResultStatus;
        this.trelloOrderCardId = trelloOrderCardId;
    }
}
