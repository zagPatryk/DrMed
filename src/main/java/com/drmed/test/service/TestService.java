package com.drmed.test.service;

import com.drmed.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.additional.exceptions.dataNotFoundInDatabase.WorkstationNotFoundException;
import com.drmed.test.domain.Test;
import com.drmed.test.mapper.TestMapper;
import com.drmed.test.dto.TestDto;
import com.drmed.test.dto.TestInfoDto;
import com.drmed.test.repository.TestRepository;
import com.drmed.workstation.domain.Workstation;
import com.drmed.workstation.dto.WorkstationInfoDto;
import com.drmed.workstation.service.WorkstationService;
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

//    public List<Test> findAllTests() {
//        List<Test> testList = new ArrayList<>();
//        testCrudRepository.findAll().forEach(testList::add);
//        return testList;
//    }
//
//    public Test findTestById(Long testId) {
//        TestHibernate testHibernate = testCrudRepository.findById(testId).orElseGet(TestHibernate::new);
//        return testMapper.mapToTest(testHibernate);
//    }
//
//    public Test saveTest(TestDto testDto) throws WorkstationNotFoundException {
//        Test test;
//        if (testDto.getId() != null) {
//            Optional<Test> optionalTest = testCrudRepository.findById(testDto.getId());
//            if (optionalTest.isPresent()) {
//                test = optionalTest.get();
//            } else {
//                test = new Test();
//                test.setId(testDto.getId());
//            }
//        } else {
//            test = new Test();
//        }
//        test.setCode(testDto.getCode());
//        test.setName(testDto.getName());
//        test.setOrderedTest(new ArrayList<>());
//
//        List<Workstation> workstationList = new ArrayList<>();
//        if (testDto.getPerformingWorkstationsIds() != null) {
//            for (Long workstationId : testDto.getPerformingWorkstationsIds()) {
//                Optional<Workstation> optionalWorkstation = workstationCrudRepository.findById(workstationId);
//                if (optionalWorkstation.isPresent()) {
//                    workstationList.add(optionalWorkstation.get());
//                } else {
//                    throw new WorkstationNotFoundException();
//                }
//            }
//            test.setPerformingWorkstations(workstationList);
//            for (Workstation workstation : workstationList) {
//                workstation.getAvailableTests().add(test);
//                workstationCrudRepository.save(workstation);
//            }
//        }
//        return testCrudRepository.save(test);
//    }
}
