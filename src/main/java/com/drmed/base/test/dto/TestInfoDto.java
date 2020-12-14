package com.drmed.base.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TestInfoDto {
    private Long id;
    private String code;
    private String name;
}
