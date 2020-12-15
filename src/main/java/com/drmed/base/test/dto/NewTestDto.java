package com.drmed.base.test.dto;

import com.drmed.base.additional.statuses.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewTestDto {
    private String code;
    private String name;
    private ActivityStatus activityStatus;
    private List<Long> performingWorkstationsIds;
}
