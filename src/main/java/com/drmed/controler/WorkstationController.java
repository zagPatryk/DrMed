package com.drmed.controler;

import com.drmed.mapper.WorkstationMapper;
import com.drmed.service.WorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/workstation")
public class WorkstationController {
    @Autowired
    private WorkstationService workstationService;
    @Autowired
    private WorkstationMapper workstationMapper;

//    @RequestMapping(method = RequestMethod.GET, value = "getWorkstationByID")
//    public List<PatientDto> getAllPatients(@RequestParam Long workstationId) {
//        return workstationMapper.mapToWorkstationDto(workstationService.getWorkstationByID(workstationId));
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "getAllWorkstation")
//    public List<PatientDto> getAllPatients() {
//        return workstationMapper.mapToWorkstationDto(workstationService.getAllWorkstation());
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "getOrderableTestsAtWorkstation")
//    public List<PatientDto> getOrderableTestsAtWorkstation(@RequestParam Long workstationId) {
//        return workstationMapper.mapToWorkstationDto(workstationService.getOrderableTestsAtWorkstation(workstationId));
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "addWorkstationToTest")
//    public List<PatientDto> addWorkstationToTest() {
//        return workstationMapper.mapToWorkstationDto(workstationService.addWorkstationToTest());
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "addWorkstation")
//    public List<PatientDto> addWorkstation(@RequestBody WorkstationDto workstationDto) {
//        return workstationMapper.mapToWorkstationDto(
//                workstationService.addWorkstation(workstationMapper.mapToWorkstation(workstationDto))
//        );
//    }
}
