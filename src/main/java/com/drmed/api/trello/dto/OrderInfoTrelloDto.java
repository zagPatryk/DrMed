package com.drmed.api.trello.dto;

import com.drmed.base.order.dto.OrderInfoDto;
import com.drmed.base.orderedTest.dto.OrderedTestInfoDto;
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
    private String code;
    private List<OrderedTestInfoDto> orderedTests;
    private String trelloCardId;
}
