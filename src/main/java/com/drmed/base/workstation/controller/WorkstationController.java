package com.drmed.base.workstation.controller;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.workstation.dto.WorkstationDto;
import com.drmed.base.workstation.dto.WorkstationInfoDto;
import com.drmed.base.workstation.service.WorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/workstation")
public class WorkstationController {
    @Autowired
    private WorkstationService workstationService;

    @GetMapping(value = "getWorkstationByID")
    public WorkstationDto getWorkstationByID(@RequestParam Long workstationId)  throws DataNotFoundInDatabase {
        return workstationService.getWorkstationDtoById(workstationId);
    }

    @GetMapping(value = "getWorkstationsByCode")
    public List<WorkstationInfoDto> getWorkstationsByCode(@RequestParam String code) {
        return workstationService.getWorkstationsByCode(code);
    }

    @GetMapping(value = "getWorkstationsByName")
    public List<WorkstationInfoDto> getWorkstationsByName(@RequestParam String name) {
        return workstationService.getWorkstationsByName(name);
    }

    @GetMapping(value = "getAllWorkstation")
    public List<WorkstationInfoDto> getAllWorkstationsList() {
        return workstationService.getAllWorkstation();
    }

    @PostMapping(value = "addWorkstation")
    public WorkstationDto addWorkstation(@RequestBody WorkstationDto workstationDto) {
        return workstationService.addWorkstation(workstationDto);
    }

    @PutMapping(value = "updateWorkstation")
    public WorkstationDto updateWorkstation(@RequestBody WorkstationDto workstationDto)  throws DataNotFoundInDatabase {
        return workstationService.updateWorkstation(workstationDto);
    }
}
