package com.drmed.domain.test;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    public TestDto(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
