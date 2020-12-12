package com.drmed.order.dto;

import com.drmed.orderedTest.dto.OrderedTestDto;
import com.drmed.patient.dto.PatientDto;
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
