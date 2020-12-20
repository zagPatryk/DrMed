package com.drmed.base.workstation.domain;

import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.test.domain.Test;
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

    public static class WorkstationBuilder {
        private Long id;
        private String code;
        private String name;
        private List<Long> availableTestsIds = new ArrayList<>();
        private ActivityStatus workstationStatus;
        private List<Test> availableTests = new ArrayList<>();

        public WorkstationBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public WorkstationBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public WorkstationBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public WorkstationBuilder setAvailableTestsIds(List<Long> availableTestsIds) {
            this.availableTestsIds = availableTestsIds;
            return this;
        }

        public WorkstationBuilder setWorkstationStatus(ActivityStatus workstationStatus) {
            this.workstationStatus = workstationStatus;
            return this;
        }

        public Workstation build() {
            return new Workstation(id, code, name, availableTestsIds, workstationStatus, availableTests);
        }
    }
}
