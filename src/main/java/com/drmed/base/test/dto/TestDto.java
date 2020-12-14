package com.drmed.base.test.dto;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.workstation.dto.WorkstationInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TestDto {
    private Long id;
    private String code;
    private String name;
    private ActivityStatus activityStatus;
    private List<WorkstationInfoDto> performingWorkstations;
}
