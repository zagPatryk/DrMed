package com.drmed.domain.order;

import com.drmed.domain.additional.Status;
import com.drmed.domain.ordered.OrderedTestDto;
import com.drmed.domain.patient.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Integer code;
    private PatientDto patient;
    private List<OrderedTestDto> orderedTests;
    private Status orderStatus;

    public OrderDto(Integer code, PatientDto patient, List<OrderedTestDto> orderedTests) {
        this.code = code;
        this.patient = patient;
        this.orderedTests = orderedTests;
        this.orderStatus = Status.PENDING;
    }

    public OrderDto() {

    }
}
