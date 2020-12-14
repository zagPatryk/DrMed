package com.drmed.base.patient.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.base.doctor.service.DoctorService;
import com.drmed.base.patient.domain.Patient;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.dto.PatientInfoDto;
import com.drmed.base.patient.mapper.PatientMapper;
import com.drmed.base.patient.repository.PatientRepository;
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
    private DoctorService doctorService;

    public PatientDto getPatientDtoById(Long id) throws PatientNotFoundException {
        return patientMapper.mapToPatientDto(patientRepository.getPatientById(id));
    }

    public Patient getPatientById(Long id) throws PatientNotFoundException {
        return patientRepository.getPatientById(id);
    }

    public List<PatientInfoDto> getPatientsByMRNContains(String MRN) {
        return patientMapper.mapToPatientInfoDtoList(patientRepository.getPatientsByMRNContains(MRN));
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

    public PatientDto addNewPatient(NewPatientDto newPatientDto) throws DoctorNotFoundException {
        Patient patient = patientMapper.mapToPatient(newPatientDto);
        mapDoctorIdToDoctor(patient);
        return patientMapper.mapToPatientDto(patientRepository.savePatient(patient));
    }

    public PatientDto updatePatient(PatientDto patientDto) throws PatientNotFoundException, DoctorNotFoundException {
        Patient patient = patientRepository.getPatientById(patientDto.getId());
        patient.setMRN(patientDto.getMRN());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setBirthDate(patientDto.getBirthDate());
        if (!patientDto.getDoctor().getId().equals(patient.getDoctorId()) ||
                !patientDto.getDoctor().getId().equals(patient.getDoctor().getId())) {
            doctorService.removePatientFromDoctorList(patient.getDoctorId(), patient.getId());
            patient.setDoctorId(patientDto.getDoctor().getId());
        }
        mapDoctorIdToDoctor(patient);
        return patientMapper.mapToPatientDto(patientRepository.savePatient(patient));
    }

    public Patient mapDoctorIdToDoctor(Patient patient) throws DoctorNotFoundException {
        patient.setDoctor(doctorService.getDoctorById(patient.getDoctorId()));
        return patient;
    }

}


