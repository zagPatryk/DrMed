package com.drmed.controler;

import com.drmed.domain.exceptions.TestNotFoundException;
import com.drmed.domain.exceptions.WorkstationNotFoundException;
import com.drmed.domain.workstation.WorkstationDto;
import com.drmed.mapper.WorkstationMapper;
import com.drmed.service.WorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/workstation")
public class WorkstationController {
    @Autowired
    private WorkstationService workstationService;
    @Autowired
    private WorkstationMapper workstationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getWorkstationByID")
    public WorkstationDto getWorkstationByID(@RequestParam Long workstationId) {
        return workstationMapper.mapToWorkstationDto(workstationService.getWorkstationByID(workstationId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllWorkstation")
    public List<WorkstationDto> getAllPatients() {
        return workstationMapper.mapToWorkstationDtoList(workstationService.getAllWorkstation());
    }


    @RequestMapping(method = RequestMethod.GET, value = "addWorkstation")
    public WorkstationDto addWorkstation(@RequestBody WorkstationDto workstationDto) {
        return workstationMapper.mapToWorkstationDto(workstationService.addWorkstation(workstationDto)
        );
    }

    @RequestMapping(method = RequestMethod.PUT, value = "connectWorkstationAndTest")
    public WorkstationDto connectWorkstationAndTest( @RequestParam Long workstationId, @RequestParam Long testId)
            throws TestNotFoundException, WorkstationNotFoundException {
        return workstationMapper.mapToWorkstationDto(workstationService.connectWorkstationAndTest(workstationId, testId));
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getOrderableTestsAtWorkstation")
//    public List<PatientDto> getOrderableTestsAtWorkstation(@RequestParam Long workstationId) {
//        return workstationMapper.mapToWorkstationDto(workstationService.getOrderableTestsAtWorkstation(workstationId));
//    }
}
