package com.drmed.base.test.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.WorkstationNotFoundException;
import com.drmed.base.test.domain.Test;
import com.drmed.base.test.mapper.TestMapper;
import com.drmed.base.test.dto.TestDto;
import com.drmed.base.test.dto.TestInfoDto;
import com.drmed.base.test.repository.TestRepository;
import com.drmed.base.workstation.domain.Workstation;
import com.drmed.base.workstation.dto.WorkstationInfoDto;
import com.drmed.base.workstation.service.WorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public TestDto addTest(TestDto testDto) {
        Test test = testMapper.mapToTest(testDto);
        mapWorkstationIdsToWorkstationList(testDto, test);
        return testMapper.mapToTestDto(testRepository.saveTest(test));
    }

    public TestDto updateTest(TestDto testDto) throws TestNotFoundException {
        Test test = testRepository.getTestById(testDto.getId());
        test.setCode(testDto.getCode());
        test.setName(testDto.getName());
        test.setTestActivityStatus(testDto.getActivityStatus());
        mapWorkstationIdsToWorkstationList(testDto, test);
        return testMapper.mapToTestDto(testRepository.saveTest(test));
    }

    private void mapWorkstationIdsToWorkstationList(TestDto testDto, Test test) {
        List<Long> workstationIdsList = testDto.getPerformingWorkstations().stream()
                .map(WorkstationInfoDto::getId).collect(Collectors.toList());
        test.setPerformingWorkstationsIds(workstationIdsList);
        test.setPerformingWorkstationList(workstationIdsList.stream()
                .map(workstationId -> {
                    try {
                        return workstationService.getWorkstationById(workstationId);
                    } catch (WorkstationNotFoundException e) {
                        e.printStackTrace();
                        return new Workstation();
                    }
                })
                .collect(Collectors.toList()));
    }
}
