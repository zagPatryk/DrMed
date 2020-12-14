package com.drmed.base.patient.controller;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.dto.PatientInfoDto;
import com.drmed.base.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping(value = "getPatientById")
    public PatientDto getPatientById(@RequestParam Long patientId) throws DataNotFoundInDatabase {
        return patientService.getPatientDtoById(patientId);
    }

    @GetMapping(value = "getAllPatients")
    public List<PatientInfoDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(value = "getPatientsByCodeContains")
    public List<PatientInfoDto> getPatientsByCodeContains(@RequestParam String code) {
        return patientService.getPatientsByCodeContains(code);
    }

    @GetMapping(value = "getPatientsByFirstNameContains")
    public List<PatientInfoDto> getPatientsByFirstNameContains(@RequestParam String firstName) {
        return patientService.getPatientsByFirstNameContains(firstName);
    }

    @GetMapping(value = "getPatientsByLastNameContains")
    public List<PatientInfoDto> getPatientsByLastNameContains(@RequestParam String lastName) {
        return patientService.getPatientsByLastNameContains(lastName);
    }

    @GetMapping(value = "getPatientsByFirstNameContainsAndLastNameContains")
    public List<PatientInfoDto> getPatientsByFirstNameContainsAndLastNameContains(@RequestParam String firstName, @RequestParam String lastName) {
        return patientService.getPatientsByFirstNameContainsAndLastNameContains(firstName, lastName);
    }

    @PostMapping(value = "addNewPatient", consumes = APPLICATION_JSON_VALUE)
    public PatientDto addNewPatient(@RequestBody NewPatientDto newPatientDto) throws DataNotFoundInDatabase {
        return patientService.addNewPatient(newPatientDto);
    }

    @PutMapping(value = "updatePatient", consumes = APPLICATION_JSON_VALUE)
    public PatientDto updatePatient(@RequestBody PatientDto patientDto) throws DataNotFoundInDatabase {
        return patientService.updatePatient(patientDto);
    }
}
