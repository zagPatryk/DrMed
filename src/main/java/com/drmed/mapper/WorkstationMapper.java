package com.drmed.mapper;

import com.drmed.domain.test.Test;
import com.drmed.domain.workstation.Workstation;
import com.drmed.domain.workstation.WorkstationDto;
import com.drmed.repository.TestRepository;
import com.drmed.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WorkstationMapper {

    @Autowired
    private WorkstationRepository workstationRepository;
    @Autowired
    private TestRepository testRepository;

    public Workstation mapToWorkstation(WorkstationDto workstationDto) {
        Workstation workstation = new Workstation();
        if (workstationDto.getId() != null) {
            Optional<Workstation> workstationFromDb = workstationRepository.findById(workstationDto.getId());
            if (workstationFromDb.isPresent()) {
                workstation = workstationFromDb.get();
            }
        }
        workstation.setCode(workstationDto.getCode());
        workstation.setName(workstationDto.getName());
        for (Long testId : workstationDto.getAvailableTestsIds()) {
            Optional<Test> optionalTest = testRepository.findById(testId);
            if(optionalTest.isPresent()) {
                workstation.getAvailableTests().add(optionalTest.get());
            }
        }
        return workstation;
    }

    public WorkstationDto mapToWorkstationDto(Workstation workstation) {
        return new WorkstationDto(
                workstation.getId(),
                workstation.getCode(),
                workstation.getName(),
                workstation.getAvailableTests().stream()
                        .map(Test::getId)
                        .collect(Collectors.toList())
        );
    }

    public List<WorkstationDto> mapToWorkstationDtoList(List<Workstation> workstationList) {
        return workstationList.stream()
                .map(this::mapToWorkstationDto)
                .collect(Collectors.toList());
    }
}
