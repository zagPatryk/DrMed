package com.drmed.domain.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TestDto {
    private Long id;
    private String code;
    private String name;
    private List<Long> performingWorkstationsIds;
    private List<Long> orderedTest;

    public TestDto(String code, String name, List<Long> performingWorkstationsIds) {
        this.code = code;
        this.name = name;
        this.performingWorkstationsIds = performingWorkstationsIds;
    }
}
