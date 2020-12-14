package com.drmed.base.workstation.dto;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.test.dto.TestInfoDto;
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
    private List<TestInfoDto> availableTests;
    private ActivityStatus activityStatus;
}
