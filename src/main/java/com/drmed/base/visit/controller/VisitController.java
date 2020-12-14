package com.drmed.base.visit.controller;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.visit.dto.NewVisitDto;
import com.drmed.base.visit.dto.VisitDto;
import com.drmed.base.visit.dto.VisitInfoDto;
import com.drmed.base.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/visit")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @GetMapping(value = "getVisitById")
    public VisitDto getVisitById(@RequestParam Long visitId) throws DataNotFoundInDatabase {
        return visitService.getVisitDtoById(visitId);
    }

    @GetMapping(value = "getAllVisitsForPatient")
    public List<VisitInfoDto> getAllVisitsForPatient(@RequestParam Long patientId) {
        return visitService.getAllVisitsForPatient(patientId);
    }

    @GetMapping(value = "getVisitsByCodeContains")
    public List<VisitInfoDto> getVisitsByCodeContains(@RequestParam String code) {
        return visitService.getVisitsByCodeContains(code);
    }

    @PostMapping(value = "addNewVisit")
    public VisitDto addNewVisit(@RequestBody NewVisitDto newVisitDto) throws DataNotFoundInDatabase {
        return visitService.addNewVisit(newVisitDto);
    }

    @PostMapping(value = "updateVisit")
    public VisitDto updateVisit(@RequestBody VisitDto visitDto) throws DataNotFoundInDatabase {
        return visitService.updateVisit(visitDto);
    }
}