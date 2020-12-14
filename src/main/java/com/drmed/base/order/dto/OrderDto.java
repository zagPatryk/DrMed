package com.drmed.base.order.dto;

import com.drmed.base.orderedTest.dto.OrderedTestDto;
import com.drmed.base.patient.dto.PatientDto;
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
    private Integer code;
    private PatientDto patient;
    private List<OrderedTestDto> orderedTests;
}
