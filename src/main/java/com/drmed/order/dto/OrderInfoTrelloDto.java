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
public class OrderInfoTrelloDto extends OrderInfoDto {
    private Integer code;
    private List<OrderedTestInfoDto> orderedTests;
    private String trelloCardId;
}
