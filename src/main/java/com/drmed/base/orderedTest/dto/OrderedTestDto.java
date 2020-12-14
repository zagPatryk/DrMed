package com.drmed.base.orderedTest.dto;

import com.drmed.base.test.dto.TestDto;
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
    private String results;
}
