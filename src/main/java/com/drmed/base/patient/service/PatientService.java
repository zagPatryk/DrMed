package com.drmed.base.patient.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.base.patient.domain.Patient;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.dto.PatientInfoDto;
import com.drmed.base.patient.mapper.PatientMapper;
import com.drmed.base.patient.repository.PatientRepository;
import com.drmed.base.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private VisitService visitService;

    public PatientDto getPatientDtoById(Long id) throws PatientNotFoundException {
        return patientMapper.mapToPatientDto(patientRepository.getPatientById(id));
    }

    public Patient getPatientById(Long id) throws PatientNotFoundException {
        return patientRepository.getPatientById(id);
    }

    public List<PatientInfoDto> getPatientsByCodeContains(String MRN) {
        return patientMapper.mapToPatientInfoDtoList(patientRepository.getPatientsByCodeContains(MRN));
    }

    public List<PatientInfoDto> getPatientsByFirstNameContains(String firstName) {
        return patientMapper.mapToPatientInfoDtoList(patientRepository.getPatientsByFirstNameContains(firstName));
    }

    public List<PatientInfoDto> getPatientsByLastNameContains(String lastName) {
        return patientMapper.mapToPatientInfoDtoList(patientRepository.getPatientsByLastNameContains(lastName));
    }

    public List<PatientInfoDto> getPatientsByFirstNameContainsAndLastNameContains(String firstName, String lastName) {
        return patientMapper.mapToPatientInfoDtoList(patientRepository.getPatientsByFirstNameContainsAndLastNameContains(firstName, lastName));
    }

    public List<PatientInfoDto> getAllPatients() {
        return patientMapper.mapToPatientInfoDtoList(patientRepository.getAllPatients());
    }

    public PatientDto addNewPatient(NewPatientDto newPatientDto) throws VisitNotFoundException {
        Patient patient = patientMapper.mapToPatient(newPatientDto);
        mapVisitIdToVisit(patient);
        return patientMapper.mapToPatientDto(patientRepository.savePatient(patient));
    }

    public PatientDto updatePatient(PatientDto patientDto) throws PatientNotFoundException, VisitNotFoundException {
        Patient patient = patientRepository.getPatientById(patientDto.getId());
        patient.setCode(patientDto.getCode());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setBirthDate(patientDto.getBirthDate());
        mapVisitIdToVisit(patient);
        return patientMapper.mapToPatientDto(patientRepository.savePatient(patient));
    }

    public Patient mapVisitIdToVisit(Patient patient) throws VisitNotFoundException {
        for (Long visitId :  patient.getVisitIdList()) {
            patient.getVisitList().add(visitService.getVisitById(visitId));
        }
        return patient;
    }

}


