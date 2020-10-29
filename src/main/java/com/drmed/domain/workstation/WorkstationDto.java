package com.drmed.domain.workstation;

import com.drmed.domain.additional.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WorkstationDto {
    private Long id;
    private String code;
    private String name;
    private List<Long> availableTestsIds;
    private ActivityStatus activityStatus = ActivityStatus.ACTIVE;

    public WorkstationDto(String code, String name, List<Long> availableTestsIds) {
        this.code = code;
        this.name = name;
        this.availableTestsIds = availableTestsIds;
    }

    public WorkstationDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public WorkstationDto(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
