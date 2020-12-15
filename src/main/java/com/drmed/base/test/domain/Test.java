package com.drmed.base.test.domain;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.orderedTest.domain.OrderedTest;
import com.drmed.base.workstation.domain.Workstation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Test implements Serializable {
    private Long id;
    private String code;
    private String name;
    private ActivityStatus testActivityStatus = ActivityStatus.ACTIVE;

    private List<Long> performingWorkstationsIds = new ArrayList<>();
    private List<Workstation> performingWorkstationList;

    private List<Long> orderedTestIds = new ArrayList<>();
    private List<OrderedTest> orderedTestList;

    public Test(Long id, String code, String name, ActivityStatus testActivityStatus, List<Long> performingWorkstationsIds) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.testActivityStatus = testActivityStatus;
        this.performingWorkstationsIds = performingWorkstationsIds;
    }

    public Test(String code, String name, ActivityStatus testActivityStatus, List<Long> performingWorkstationsIds) {
        this.code = code;
        this.name = name;
        this.testActivityStatus = testActivityStatus;
        this.performingWorkstationsIds = performingWorkstationsIds;
    }
}


