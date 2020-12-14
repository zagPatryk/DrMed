package com.drmed.base.order.dto;

import com.drmed.base.orderedTest.dto.OrderedTestDto;
import com.drmed.base.visit.dto.VisitInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String code;
    private VisitInfoDto visit;
    private List<OrderedTestDto> orderedTests;
}
