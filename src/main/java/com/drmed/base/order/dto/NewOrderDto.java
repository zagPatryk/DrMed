package com.drmed.base.order.dto;

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
    private String code;
    private Long visitId;
    private List<Long> testsIds;
}
