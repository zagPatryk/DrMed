package com.drmed.doctor.service;

import com.drmed.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.additional.statuses.ActivityStatus;
import com.drmed.doctor.domain.Doctor;
import com.drmed.doctor.dto.DoctorDto;
import com.drmed.doctor.dto.DoctorInfoDto;
import com.drmed.doctor.dto.NewDoctorDto;
import com.drmed.doctor.mapper.DoctorMapper;
import com.drmed.doctor.repository.DoctorRepository;
import com.drmed.patient.domain.Patient;
import com.drmed.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private PatientService patientService;

    public DoctorDto getDoctorDtoById(Long doctorId) throws DoctorNotFoundException {
        return doctorMapper.mapToDoctorDto(doctorRepository.getDoctorById(doctorId));
    }

    public Doctor getDoctorById(Long doctorId) throws DoctorNotFoundException {
        return doctorRepository.getDoctorById(doctorId);
    }

    public List<DoctorInfoDto> getDoctorByPrimaryIdContains(String primaryId) {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getDoctorByPrimaryIdContains(primaryId));
    }

    public List<DoctorInfoDto> getDoctorByFirstNameContains(String firstName) {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getDoctorByFirstNameContains(firstName));
    }

    public List<DoctorInfoDto> getDoctorByLastNameContains(String lastName) {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getDoctorByLastNameContains(lastName));
    }

    public List<DoctorInfoDto> getDoctorByFirstNameContainsAndLastNameContaining(String firstName, String lastName) {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getDoctorByFirstNameContainsAndLastNameContaining(firstName, lastName));
    }

    public List<DoctorInfoDto> getAllDoctors() {
        return doctorMapper.mapToDoctorInfoDtoList(doctorRepository.getAllDoctors());
    }

    public DoctorDto addNewDoctor(NewDoctorDto newDoctorDto) {
        Doctor doctor = new Doctor();
        doctor.setPrimaryId(newDoctorDto.getPrimaryId());
        doctor.setFirstName(newDoctorDto.getFirstName());
        doctor.setLastName(newDoctorDto.getLastName());
        doctor.setEmail(newDoctorDto.getEmail());
        doctor.setDoctorStatus(ActivityStatus.ACTIVE);
        return doctorMapper.mapToDoctorDto(doctorRepository.saveDoctor(doctor));
    }

    public DoctorDto updateDoctor(DoctorDto doctorDto) throws DoctorNotFoundException, PatientNotFoundException {
        Doctor doctor = doctorRepository.getDoctorById(doctorDto.getId());
        doctor.setPrimaryId(doctorDto.getPrimaryId());
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setDoctorStatus(doctorDto.getDoctorStatus());
        doctor.setPatientsIds(doctorDto.getPatientsIds());
        mapPatientsIdsToPatients(doctor);
        return doctorMapper.mapToDoctorDto(doctorRepository.saveDoctor(doctor));
    }

    public Doctor removePatientFromDoctorList(Long doctorId, Long patientId)
            throws DoctorNotFoundException, PatientNotFoundException {
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        Patient patient = patientService.getPatientById(patientId);
        doctor.getPatientList().remove(patient);
        return doctorRepository.saveDoctor(doctor);
    }

    public DoctorDto safeDeleteDoctorById(Long doctorId) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        doctor.setDoctorStatus(ActivityStatus.DELETED);
        return doctorMapper.mapToDoctorDto(doctorRepository.saveDoctor(doctor));
    }

    private Doctor mapPatientsIdsToPatients(Doctor doctor) throws PatientNotFoundException {
        List<Patient> patientList = new ArrayList<>();
        for (Long patientId : doctor.getPatientsIds()) {
            Patient patient = patientService.getPatientById(patientId);
            patientList.add(patient);
        }
        doctor.setPatientList(patientList);
        return doctor;
    }
}

