package com.drmed.domain.ordered;

import com.drmed.domain.additional.ResultStatus;
import com.drmed.domain.test.TestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderedTestDto {
    private Long id;
    private Long orderId;
    private TestDto test;
    private ResultStatus testResultStatus;
    private String results;

    public OrderedTestDto(Long orderId, TestDto test) {
        this.orderId = orderId;
        this.test = test;
        this.testResultStatus = ResultStatus.PENDING;
        this.results = "";
    }
}
