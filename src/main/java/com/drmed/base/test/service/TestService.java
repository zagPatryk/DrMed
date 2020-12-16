package com.drmed.base.test.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.WorkstationNotFoundException;
import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.test.domain.Test;
import com.drmed.base.test.dto.NewTestDto;
import com.drmed.base.test.dto.TestDto;
import com.drmed.base.test.dto.TestInfoDto;
import com.drmed.base.test.mapper.TestMapper;
import com.drmed.base.test.repository.TestRepository;
import com.drmed.base.workstation.domain.Workstation;
import com.drmed.base.workstation.service.WorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private WorkstationService workstationService;

    public TestDto getTestDtoById(Long testId) throws TestNotFoundException {
        return testMapper.mapToTestDto(testRepository.getTestById(testId));
    }

    public Test getTestById(Long testId) throws TestNotFoundException {
        return testRepository.getTestById(testId);
    }

    public List<TestInfoDto> getTestsByCode(String code) {
        return testMapper.mapToTestInfoDtoList(testRepository.getTestByCode(code));
    }

    public List<TestInfoDto> getTestsByName(String name) {
        return testMapper.mapToTestInfoDtoList(testRepository.getTestByName(name));
    }

    public List<TestInfoDto> getAllTests() {
        return testMapper.mapToTestInfoDtoList(testRepository.getAllTests());
    }

    public TestDto addTest(NewTestDto newTestDto) throws WorkstationNotFoundException {
        Test test = new Test();
        test.setCode(newTestDto.getCode());
        test.setName(newTestDto.getName());
        test.setPerformingWorkstationsIds(newTestDto.getPerformingWorkstationsIds());
        test.setTestActivityStatus(ActivityStatus.ACTIVE);
        mapWorkstationIdsToWorkstationList(test);
        return testMapper.mapToTestDto(testRepository.saveTest(test));
    }

    public TestDto updateTest(TestDto testDto) throws TestNotFoundException, WorkstationNotFoundException {
        Test test = testRepository.getTestById(testDto.getId());
        test.setCode(testDto.getCode());
        test.setName(testDto.getName());
        test.setTestActivityStatus(testDto.getActivityStatus());
        mapWorkstationIdsToWorkstationList(test);
        return testMapper.mapToTestDto(testRepository.saveTest(test));
    }

    private void mapWorkstationIdsToWorkstationList(Test test) throws WorkstationNotFoundException {
        List<Workstation> workstationList = new ArrayList<>();
        for (Long workstationId : test.getPerformingWorkstationsIds()) {
            workstationList.add(workstationService.getWorkstationById(workstationId));
        }
        test.setPerformingWorkstationList(workstationList);
    }
}
