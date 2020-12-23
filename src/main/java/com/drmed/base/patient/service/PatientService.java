package com.drmed.base.patient.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.base.patient.domain.Patient;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.dto.PatientInfoDto;
import com.drmed.base.patient.mapper.PatientMapper;
import com.drmed.base.patient.repository.PatientRepository;
import com.drmed.base.visit.dto.VisitInfoDto;
import com.drmed.base.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private VisitService visitService;

    public PatientDto addNewPatient(NewPatientDto newPatientDto) {
        Patient patient = new Patient.PatientBuilder()
                .setCode(newPatientDto.getCode())
                .setFirstName(newPatientDto.getFirstName())
                .setLastName(newPatientDto.getLastName())
                .setBirthDate(newPatientDto.getBirthDate())
                .setGender(newPatientDto.getGender())
                .build();
        return patientMapper.mapToPatientDto(patientRepository.savePatient(patient));
    }

    public PatientDto updatePatient(PatientDto patientDto) throws VisitNotFoundException {
        Patient patient = new Patient.PatientBuilder()
                .setId(patientDto.getId())
                .setCode(patientDto.getCode())
                .setFirstName(patientDto.getFirstName())
                .setLastName(patientDto.getLastName())
                .setBirthDate(patientDto.getBirthDate())
                .setGender(patientDto.getGender())
                .setVisitIdList(patientDto.getVisitInfoDtoList().stream().map(VisitInfoDto::getId).collect(Collectors.toList()))
                .build();
        mapVisitIdToVisit(patient);
        return patientMapper.mapToPatientDto(patientRepository.savePatient(patient));
    }

    public void mapVisitIdToVisit(Patient patient) throws VisitNotFoundException {
        for (Long visitId :  patient.getVisitIdList()) {
            patient.getVisitList().add(visitService.getVisitById(visitId));
        }
    }

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
}


