package com.drmed.domain.test;

import com.drmed.domain.additional.ActivityStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TestDto {
    private Long id;
    @NonNull
    private String code;
    @NonNull
    private String name;
    private List<Long> performingWorkstationsIds;
    private List<Long> orderedTest;
    private ActivityStatus activityStatus = ActivityStatus.ACTIVE;

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
