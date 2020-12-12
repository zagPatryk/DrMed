package com.drmed.workstation.domain;

import com.drmed.additional.statuses.ActivityStatus;
import com.drmed.test.domain.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Workstation {
    private Long id;
    private String code;
    private String name;
    private List<Long> availableTestsIds = new ArrayList<>();
    private ActivityStatus workstationStatus;
    private List<Test> availableTests;

    public Workstation(Long id, String code, String name, List<Long> availableTestsIds, ActivityStatus workstationStatus) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.availableTestsIds = availableTestsIds;
        this.workstationStatus = workstationStatus;
    }

}
