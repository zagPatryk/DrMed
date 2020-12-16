package com.drmed.base.workstation.mapper;

import com.drmed.base.test.mapper.TestMapper;
import com.drmed.base.test.dto.TestInfoDto;
import com.drmed.base.test.repository.TestHibernate;
import com.drmed.base.workstation.domain.Workstation;
import com.drmed.base.workstation.dto.WorkstationDto;
import com.drmed.base.workstation.dto.WorkstationInfoDto;
import com.drmed.base.workstation.repository.WorkstationHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkstationMapper {
    @Autowired
    private TestMapper testMapper;

    public WorkstationHibernate mapToWorkstationHibernate(Workstation workstation) {
        return new WorkstationHibernate(
                workstation.getId(),
                workstation.getCode(),
                workstation.getName(),
                testMapper.mapToTestHibernateList(workstation.getAvailableTests()),
                workstation.getWorkstationStatus()
        );
    }

    public Workstation mapToWorkstation(WorkstationHibernate workstationHibernate) {
        List<Long> workstationHibernateIdsList = workstationHibernate == null ? new ArrayList<>()
                : workstationHibernate.getAvailableTests().stream()
                .map(TestHibernate::getId).collect(Collectors.toList());
        return new Workstation(
                workstationHibernate.getId(),
                workstationHibernate.getCode(),
                workstationHibernate.getName(),
                workstationHibernateIdsList,
                workstationHibernate.getWorkstationStatus()
        );
    }

    public WorkstationInfoDto mapToWorkstationInfoDto(Workstation workstation) {
        return new WorkstationInfoDto(
                workstation.getId(),
                workstation.getCode(),
                workstation.getName()
        );
    }

    public WorkstationDto mapToWorkstationDto(Workstation workstation) {
        return new WorkstationDto(
                workstation.getId(),
                workstation.getCode(),
                workstation.getName(),
                testMapper.mapToTestInfoDtoList(workstation.getAvailableTests()),
                workstation.getWorkstationStatus()
        );
    }

    public Workstation mapToWorkstation(WorkstationDto workstationDto) {
        return new Workstation(
                workstationDto.getId(),
                workstationDto.getCode(),
                workstationDto.getName(),
                workstationDto.getAvailableTests().stream().map(TestInfoDto::getId).collect(Collectors.toList()),
                workstationDto.getActivityStatus()
        );
    }

    public List<WorkstationInfoDto> mapToWorkstationInfoDtoList(List<Workstation> workstationList) {
        return workstationList == null ? new ArrayList<>()
                : workstationList.stream().map(this::mapToWorkstationInfoDto).collect(Collectors.toList());
    }

    public List<Workstation> mapToWorkstationList(List<WorkstationHibernate> workstationHibernateList) {
        return workstationHibernateList == null ? new ArrayList<>()
                : workstationHibernateList.stream().map(this::mapToWorkstation).collect(Collectors.toList());
    }

    public List<WorkstationHibernate> mapToWorkstationHibernateList(List<Workstation> workstationList) {
        return workstationList == null ? new ArrayList<>()
                : workstationList.stream().map(this::mapToWorkstationHibernate).collect(Collectors.toList());
    }
}
