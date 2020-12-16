package com.drmed.base.test.mapper;

import com.drmed.base.orderedTest.mapper.OrderedTestMapper;
import com.drmed.base.test.domain.Test;
import com.drmed.base.test.dto.TestDto;
import com.drmed.base.test.dto.TestInfoDto;
import com.drmed.base.test.repository.TestHibernate;
import com.drmed.base.workstation.mapper.WorkstationMapper;
import com.drmed.base.workstation.repository.WorkstationHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestMapper {
    @Autowired
    private WorkstationMapper workstationMapper;
    @Autowired
    private OrderedTestMapper orderedTestMapper;

    public TestDto mapToTestDto(Test test) {
        return new TestDto(
                test.getId(),
                test.getCode(),
                test.getName(),
                test.getTestActivityStatus(),
                workstationMapper.mapToWorkstationInfoDtoList(test.getPerformingWorkstationList())
        );
    }

    public TestHibernate mapToTestHibernate(Test test) {
        return new TestHibernate(
                test.getId(),
                test.getCode(),
                test.getName(),
                workstationMapper.mapToWorkstationHibernateList(test.getPerformingWorkstationList()),
                orderedTestMapper.mapToOrderedTestHibernateList(test.getOrderedTestList()),
                test.getTestActivityStatus()
        );
    }

    public Test mapToTest(TestHibernate testHibernate) {
        return new Test(
                testHibernate.getId(),
                testHibernate.getCode(),
                testHibernate.getName(),
                testHibernate.getTestActivityStatus(),
                testHibernate.getPerformingWorkstationList().stream().map(WorkstationHibernate::getId).collect(Collectors.toList())
        );
    }

    public TestInfoDto mapToTestInfoDto(Test test) {
        return new TestInfoDto(
                test.getId(),
                test.getCode(),
                test.getName()
        );
    }

    public List<TestInfoDto> mapToTestInfoDtoList(List<Test> testList) {
        return testList == null ? new ArrayList<>()
                : testList.stream().map(this::mapToTestInfoDto).collect(Collectors.toList());
    }

    public List<Test> mapToTestList(List<TestHibernate> testList) {
        return testList == null ? new ArrayList<>()
                : testList.stream().map(this::mapToTest).collect(Collectors.toList());
    }

    public List<TestHibernate> mapToTestHibernateList(List<Test> testHibernateList) {
        return testHibernateList == null ? new ArrayList<>()
                : testHibernateList.stream().map(this::mapToTestHibernate).collect(Collectors.toList());
    }
}

