package com.drmed.base.doctor.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.doctor.domain.Doctor;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.base.doctor.repository.DoctorCrudRepository;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.repository.PatientCrudRepository;
import com.drmed.base.patient.service.PatientService;
import com.drmed.base.visit.dto.NewVisitDto;
import com.drmed.base.visit.dto.VisitDto;
import com.drmed.base.visit.repository.VisitCrudRepository;
import com.drmed.base.visit.service.VisitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class DoctorServiceTestSuite {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorCrudRepository doctorCrudRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientCrudRepository patientCrudRepository;
    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitCrudRepository visitCrudRepository;

    @Test
    public void addNewDoctor() throws DoctorNotFoundException {
        // Given
        NewDoctorDto newDoctorDto = new NewDoctorDto("doctorCode", "doctorFirstName",
                "doctorLastName", "email@email.com");

        // When
        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);
        Doctor doctorFromBase = doctorService.getDoctorById(doctorDto.getId());

        // Then
        assertEquals(doctorDto.getId(), doctorDto.getId());
        assertEquals(newDoctorDto.getCode(), doctorFromBase.getCode());
        assertEquals(newDoctorDto.getFirstName(), doctorFromBase.getFirstName());
        assertEquals(newDoctorDto.getLastName(), doctorFromBase.getLastName());
        assertEquals(newDoctorDto.getEmail(), doctorFromBase.getEmail());
        assertEquals(ActivityStatus.ACTIVE, doctorFromBase.getDoctorStatus());
        assertTrue(doctorFromBase.getVisitIdList().isEmpty());
        assertTrue(doctorFromBase.getVisitList().isEmpty());

        // Clean
        doctorCrudRepository.deleteById(doctorDto.getId());
    }

    @Test
    public void updateDoctor() throws DoctorNotFoundException, VisitNotFoundException, PatientNotFoundException {
        // Given
        NewDoctorDto newDoctorDto = new NewDoctorDto("doctorCode", "doctorFirstName",
                "doctorLastName", "email@email.com");
        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);

        NewPatientDto newPatientDto = new NewPatientDto();
        PatientDto patientDto = patientService.addNewPatient(newPatientDto);

        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
                patientDto.getId(), doctorDto.getId());
        VisitDto visitDto = visitService.addNewVisit(newVisitDto);

        // When
        doctorDto.setCode("doctorCodeChanged");
        doctorDto.setFirstName("doctorFirstNameChanged");
        doctorDto.setLastName("doctorLastNameChanged");
        doctorDto.setEmail("emailChanged@email.com");
        doctorService.updateDoctor(doctorDto);
        Doctor doctorFromBase = doctorService.getDoctorById(doctorDto.getId());

        // Then
        assertEquals(doctorDto.getId(), doctorDto.getId());
        assertEquals(doctorDto.getCode(), doctorFromBase.getCode());
        assertEquals(doctorDto.getFirstName(), doctorFromBase.getFirstName());
        assertEquals(doctorDto.getLastName(), doctorFromBase.getLastName());
        assertEquals(doctorDto.getEmail(), doctorFromBase.getEmail());
        assertEquals(ActivityStatus.ACTIVE, doctorFromBase.getDoctorStatus());
        assertTrue(visitService.getVisitById(visitDto.getId()).getDoctor().getId().equals(doctorDto.getId()));

        // Clean
        visitCrudRepository.deleteById(visitDto.getId());
        patientCrudRepository.deleteById(patientDto.getId());
        doctorCrudRepository.deleteById(doctorDto.getId());
    }

    @Test
    public void safeDeleteDoctorById() throws DoctorNotFoundException, PatientNotFoundException, VisitNotFoundException {
        // Given
        NewDoctorDto newDoctorDto = new NewDoctorDto("doctorCode", "doctorFirstName",
                "doctorLastName", "email@email.com");
        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);

        NewPatientDto newPatientDto = new NewPatientDto();
        PatientDto patientDto = patientService.addNewPatient(newPatientDto);

        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
                patientDto.getId(), doctorDto.getId());
        VisitDto visitDto = visitService.addNewVisit(newVisitDto);

        // When
        doctorService.safeDeleteDoctorById(doctorDto.getId());
        Doctor doctorFromBase = doctorService.getDoctorById(doctorDto.getId());

        // Then
        assertEquals(ActivityStatus.DELETED, doctorFromBase.getDoctorStatus());
        assertTrue(visitService.getVisitById(visitDto.getId()).getDoctor().getId().equals(doctorDto.getId()));

        // Clean
        visitCrudRepository.deleteById(visitDto.getId());
        patientCrudRepository.deleteById(patientDto.getId());
        doctorCrudRepository.deleteById(doctorDto.getId());
    }

    @Test
    public void getDoctorById() {
    }

    @Test
    public void getDoctorByPrimaryIdContains() {
    }

    @Test
    public void getDoctorByFirstNameContains() {
    }

    @Test
    public void getDoctorByLastNameContains() {
    }

    @Test
    public void getDoctorByFirstNameContainsAndLastNameContaining() {
    }

    @Test
    public void getAllDoctors() {
    }

    @Test
    public void removeVisitFromDoctorList() {
    }
}