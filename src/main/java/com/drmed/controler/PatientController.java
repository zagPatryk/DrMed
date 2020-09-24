package com.drmed.controler;

import com.drmed.domain.patient.PatientDto;
import com.drmed.mapper.PatientMapper;
import com.drmed.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientMapper patientMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getPatientById")
    public PatientDto getPatientById(@RequestParam Long patientId) {
        return patientMapper.mapToPatientDto(patientService.getPatientById(patientId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllPatients")
    public List<PatientDto> getAllPatients() {
        return patientMapper.mapToPatientDtoList(patientService.getAllPatients());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addNewPatient", consumes = APPLICATION_JSON_VALUE)
    public PatientDto getAllPatients(@RequestBody PatientDto patientDto) {
        return patientMapper.mapToPatientDto(patientService.savePatient(patientMapper.mapToPatient(patientDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updatePatient", consumes = APPLICATION_JSON_VALUE)
    public PatientDto updatePatient(@RequestBody PatientDto patientDto) {
        return patientMapper.mapToPatientDto(patientService.savePatient(patientMapper.mapToPatient(patientDto)));
    }
}
