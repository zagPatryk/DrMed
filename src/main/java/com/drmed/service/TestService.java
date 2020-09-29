package com.drmed.service;

import com.drmed.domain.exceptions.WorkstationNotFoundException;
import com.drmed.domain.test.Test;
import com.drmed.domain.test.TestDto;
import com.drmed.domain.workstation.Workstation;
import com.drmed.repository.TestRepository;
import com.drmed.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private WorkstationRepository workstationRepository;

    public List<Test> findAllTests() {
        List<Test> testList = new ArrayList<>();
        testRepository.findAll().forEach(testList::add);
        return testList;
    }

    public Test findTestById(Long id) {
        return testRepository.findById(id).orElseGet(Test::new);
    }

    public Test addNewTest(TestDto testDto) throws WorkstationNotFoundException {
        Test test;
        if (testDto.getId() != null) {
            Optional<Test> optionalTest = testRepository.findById(testDto.getId());
            if (optionalTest.isPresent()) {
                test = optionalTest.get();
            } else {
                test = new Test();
                test.setId(testDto.getId());
            }
        } else {
            test = new Test();
        }
        test.setCode(testDto.getCode());
        test.setName(testDto.getName());
        test.setOrderedTest(new ArrayList<>());

        List<Workstation> workstationList = new ArrayList<>();
        if (testDto.getPerformingWorkstationsIds() != null) {
            for (Long workstationId : testDto.getPerformingWorkstationsIds()) {
                Optional<Workstation> optionalWorkstation = workstationRepository.findById(workstationId);
                if (optionalWorkstation.isPresent()) {
                    workstationList.add(optionalWorkstation.get());
                } else {
                    throw new WorkstationNotFoundException();
                }
            }
            test.setPerformingWorkstations(workstationList);
            for (Workstation workstation : workstationList) {
                workstation.getAvailableTests().add(test);
                workstationRepository.save(workstation);
            }
        }
        return testRepository.save(test);
    }

    public Test updateTest(TestDto testDto) throws WorkstationNotFoundException {
        Test test;
        if (testDto.getId() != null) {
            Optional<Test> optionalTest = testRepository.findById(testDto.getId());
            if (optionalTest.isPresent()) {
                test = optionalTest.get();
            } else {
                test = new Test();
                test.setId(testDto.getId());
            }
        } else {
            test = new Test();
        }
        test.setCode(testDto.getCode());
        test.setName(testDto.getName());
        test.setOrderedTest(new ArrayList<>());

        List<Workstation> workstationList = new ArrayList<>();
        if (testDto.getPerformingWorkstationsIds() != null) {
            for (Long workstationId : testDto.getPerformingWorkstationsIds()) {
                Optional<Workstation> optionalWorkstation = workstationRepository.findById(workstationId);
                if (optionalWorkstation.isPresent()) {
                    workstationList.add(optionalWorkstation.get());
                } else {
                    throw new WorkstationNotFoundException();
                }
            }
            test.setPerformingWorkstations(workstationList);
            for (Workstation workstation : workstationList) {
                workstation.getAvailableTests().add(test);
                workstationRepository.save(workstation);
            }
        }
        return testRepository.save(test);
    }

}
