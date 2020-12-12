package com.drmed.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderDto {
    private Integer code;
    private Long patientId;
    private List<Long> testsIds;
}
