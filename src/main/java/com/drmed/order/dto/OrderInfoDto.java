package com.drmed.order.dto;

import com.drmed.orderedTest.dto.OrderedTestInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDto {
    private Long id;
    private Integer code;
    private List<OrderedTestInfoDto> orderedTests;
}