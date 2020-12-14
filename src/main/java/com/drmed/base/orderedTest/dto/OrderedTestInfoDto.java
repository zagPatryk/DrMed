package com.drmed.base.orderedTest.dto;

import com.drmed.base.test.dto.TestInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderedTestInfoDto {
    private Long id;
    private TestInfoDto test;
}
