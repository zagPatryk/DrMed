package com.drmed.test.mapper;

import com.drmed.orderedTest.mapper.OrderedTestMapper;
import com.drmed.test.domain.Test;
import com.drmed.test.dto.TestDto;
import com.drmed.test.dto.TestInfoDto;
import com.drmed.test.repository.TestHibernate;
import com.drmed.workstation.mapper.WorkstationMapper;
import com.drmed.workstation.dto.WorkstationInfoDto;
import com.drmed.workstation.repository.WorkstationHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Test mapToTest(TestDto testDto) {
        return new Test(
                testDto.getId(),
                testDto.getCode(),
                testDto.getName(),
                testDto.getActivityStatus(),
                testDto.getPerformingWorkstations().stream().map(WorkstationInfoDto::getId).collect(Collectors.toList())
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
        return testList.stream().map(this::mapToTestInfoDto).collect(Collectors.toList());
    }

    public List<Test> mapToTestList(List<TestHibernate> testList) {
        return testList.stream().map(this::mapToTest).collect(Collectors.toList());
    }

    public List<TestHibernate> mapToTestHibernateList(List<Test> testHibernateList) {
        return testHibernateList.stream().map(this::mapToTestHibernate).collect(Collectors.toList());
    }
}

