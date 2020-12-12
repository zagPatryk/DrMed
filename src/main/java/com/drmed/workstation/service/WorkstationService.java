package com.drmed.workstation.service;

import com.drmed.additional.exceptions.dataNotFoundInDatabase.TestNotFoundException;
import com.drmed.additional.exceptions.dataNotFoundInDatabase.WorkstationNotFoundException;
import com.drmed.test.domain.Test;
import com.drmed.test.dto.TestInfoDto;
import com.drmed.test.service.TestService;
import com.drmed.workstation.domain.Workstation;
import com.drmed.workstation.mapper.WorkstationMapper;
import com.drmed.workstation.dto.WorkstationDto;
import com.drmed.workstation.dto.WorkstationInfoDto;
import com.drmed.workstation.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkstationService {
    @Autowired
    private WorkstationRepository workstationRepository;
    @Autowired
    private WorkstationMapper workstationMapper;
    @Autowired
    private TestService testService;

    public Workstation getWorkstationById(Long workstationId) throws WorkstationNotFoundException {
        return workstationRepository.getWorkstationById(workstationId);
    }

    public WorkstationDto getWorkstationDtoById(Long workstationId) throws WorkstationNotFoundException {
        return workstationMapper.mapToWorkstationDto(workstationRepository.getWorkstationById(workstationId));
    }

    public List<WorkstationInfoDto> getWorkstationsByCode(String code) {
        return workstationMapper.mapToWorkstationInfoDtoList(workstationRepository.getWorkstationByCode(code));
    }

    public List<WorkstationInfoDto> getWorkstationsByName(String name) {
        return workstationMapper.mapToWorkstationInfoDtoList(workstationRepository.getWorkstationByName(name));
    }

    public List<WorkstationInfoDto> getAllWorkstation() {
        return workstationMapper.mapToWorkstationInfoDtoList(workstationRepository.getAllWorkstations());
    }

    public WorkstationDto addWorkstation(WorkstationDto workstationDto) {
        Workstation workstation = workstationMapper.mapToWorkstation(workstationDto);
        mapTestIdsToTestList(workstationDto, workstation);
        return workstationMapper.mapToWorkstationDto(workstationRepository.saveWorkstation(workstation));
    }

    public WorkstationDto updateWorkstation(WorkstationDto workstationDto) throws WorkstationNotFoundException {
        Workstation workstation = workstationRepository.getWorkstationById(workstationDto.getId());
        workstation.setCode(workstationDto.getCode());
        workstation.setName(workstationDto.getName());
        mapTestIdsToTestList(workstationDto, workstation);
        return workstationMapper.mapToWorkstationDto(workstationRepository.saveWorkstation(workstation));
    }

    private void mapTestIdsToTestList(WorkstationDto workstationDto, Workstation workstation) {
        List<Long> testIdsList = workstationDto.getAvailableTests().stream()
                .map(TestInfoDto::getId).collect(Collectors.toList());
        workstation.setAvailableTestsIds(testIdsList);
        workstation.setAvailableTests(testIdsList.stream()
                .map(testId -> {
                    try {
                        return testService.getTestById(testId);

                    } catch (TestNotFoundException e) {
                        e.printStackTrace();
                        return new Test();
                    }
                }).collect(Collectors.toList()));
    }


}