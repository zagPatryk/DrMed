package com.drmed.orderedTest.dto;

import com.drmed.test.dto.TestDto;
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
