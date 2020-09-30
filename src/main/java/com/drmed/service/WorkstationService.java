package com.drmed.service;

import com.drmed.domain.exceptions.TestNotFoundException;
import com.drmed.domain.exceptions.WorkstationNotFoundException;
import com.drmed.domain.test.Test;
import com.drmed.domain.workstation.Workstation;
import com.drmed.domain.workstation.WorkstationDto;
import com.drmed.repository.TestRepository;
import com.drmed.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkstationService {

    @Autowired
    private WorkstationRepository workstationRepository;
    @Autowired
    private TestRepository testRepository;

    public Workstation getWorkstationByID(Long workstationId) {
        return workstationRepository.findById(workstationId).orElseGet(Workstation::new);
    }

    public List<Workstation> getAllWorkstation() {
        List<Workstation> workstationList = new ArrayList<>();
        workstationRepository.findAll().forEach(workstationList::add);
        return workstationList;
    }

    public Workstation connectWorkstationAndTest(Long workstationId, Long testId)
            throws TestNotFoundException, WorkstationNotFoundException {
        Test test;
        Optional<Test> optionalTest = testRepository.findById(testId);

        if (optionalTest.isPresent()) {
            test = optionalTest.get();
        } else {
            throw new TestNotFoundException();
        }

        Workstation workstation;
        Optional<Workstation> optionalWorkstation = workstationRepository.findById(workstationId);

        if (optionalWorkstation.isPresent()) {
            workstation = optionalWorkstation.get();
        } else {
            throw new WorkstationNotFoundException();
        }

        test.getPerformingWorkstations().add(workstation);
        workstation.getAvailableTests().add(test);
        workstationRepository.save(workstation);
        testRepository.save(test);
        return workstation;
    }

    public Workstation addWorkstation(WorkstationDto workstationDto) {
        Workstation workstation = new Workstation();
        if (workstationDto.getId() != null) {
            Optional<Workstation> optionalWorkstation = workstationRepository.findById(workstationDto.getId());
            if (optionalWorkstation.isPresent()) {
                workstation = optionalWorkstation.get();
            } else {
                workstation = new Workstation();
                workstation.setId(workstationDto.getId());
            }
        }
        workstation.setCode(workstationDto.getCode());
        workstation.setName(workstationDto.getName());
        for (Long testId : workstationDto.getAvailableTestsIds()) {
            Optional<Test> optionalTest = testRepository.findById(testId);
            if (optionalTest.isPresent()) {
                Test test = optionalTest.get();
                workstation.getAvailableTests().add(test);
                test.getPerformingWorkstations().add(workstation);
                testRepository.save(test);
            }
        }
        return workstationRepository.save(workstation);
    }

    /*
    @RequestMapping(method = RequestMethod.GET, value = "getOrderableTestsAtWorkstation")
    public List<PatientDto> getOrderableTestsAtWorkstation(@RequestParam Long workstationId) {
        return workstationMapper.mapToWorkstationDto(workstationService.getOrderableTestsAtWorkstation(workstationId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "addWorkstation")
    public List<PatientDto> addWorkstation(@RequestBody WorkstationDto workstationDto) {
        return workstationMapper.mapToWorkstationDto(
                workstationService.addWorkstation(workstationMapper.mapToWorkstation(workstationDto))
        );
    }
     */
}
