package com.drmed.base.test.domain;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.orderedTest.domain.OrderedTest;
import com.drmed.base.workstation.domain.Workstation;
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
public class Test {
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

    public static class TestBuilder {
        private Long id;
        private String code;
        private String name;
        private ActivityStatus testActivityStatus = ActivityStatus.ACTIVE;

        private List<Long> performingWorkstationsIds = new ArrayList<>();
        private List<Workstation> performingWorkstationList;

        private List<Long> orderedTestIds = new ArrayList<>();
        private List<OrderedTest> orderedTestList;

        public TestBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public TestBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public TestBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TestBuilder setTestActivityStatus(ActivityStatus testActivityStatus) {
            this.testActivityStatus = testActivityStatus;
            return this;
        }

        public TestBuilder setPerformingWorkstationsIds(List<Long> performingWorkstationsIds) {
            this.performingWorkstationsIds = performingWorkstationsIds;
            return this;
        }

        public TestBuilder setPerformingWorkstationList(List<Workstation> performingWorkstationList) {
            this.performingWorkstationList = performingWorkstationList;
            return this;
        }

        public TestBuilder setOrderedTestIds(List<Long> orderedTestIds) {
            this.orderedTestIds = orderedTestIds;
            return this;
        }

        public TestBuilder setOrderedTestList(List<OrderedTest> orderedTestList) {
            this.orderedTestList = orderedTestList;
            return this;
        }

       public Test build() {
            return new Test(id, code, name, testActivityStatus, performingWorkstationsIds, performingWorkstationList, orderedTestIds, orderedTestList);
       }
    }
}


