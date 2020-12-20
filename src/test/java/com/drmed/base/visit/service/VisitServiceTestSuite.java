package com.drmed.base.visit.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.VisitNotFoundException;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.base.doctor.repository.DoctorCrudRepository;
import com.drmed.base.doctor.service.DoctorService;
import com.drmed.base.patient.dto.NewPatientDto;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.repository.PatientCrudRepository;
import com.drmed.base.patient.service.PatientService;
import com.drmed.base.visit.domain.Visit;
import com.drmed.base.visit.dto.NewVisitDto;
import com.drmed.base.visit.dto.VisitDto;
import com.drmed.base.visit.repository.VisitCrudRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class VisitServiceTestSuite {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private PatientCrudRepository patientCrudRepository;
    @Autowired
    private DoctorCrudRepository doctorCrudRepository;
    @Autowired
    private VisitCrudRepository visitCrudRepository;

    @Test
    void addNewVisit() throws DoctorNotFoundException, PatientNotFoundException, VisitNotFoundException {
        // Given
        NewPatientDto newPatientDto = new NewPatientDto();
        PatientDto patientDto = patientService.addNewPatient(newPatientDto);

        NewDoctorDto newDoctorDto = new NewDoctorDto();
        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);

        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
                patientDto.getId(), doctorDto.getId());

        // When
        VisitDto visitDto = visitService.addNewVisit(newVisitDto);
        Visit visitFromDatabase = visitService.getVisitById(visitDto.getId());

        // Then
        assertEquals(visitDto.getId(), visitFromDatabase.getId());
        assertEquals(newVisitDto.getCode(), visitFromDatabase.getCode());
        assertEquals(newVisitDto.getDoctorId(), visitFromDatabase.getDoctor().getId());
        assertEquals(newVisitDto.getPatientId(), visitFromDatabase.getPatient().getId());
        assertEquals(newVisitDto.getDateOfVisit(), visitFromDatabase.getDateOfVisit());

        // Clean
        visitCrudRepository.deleteById(visitDto.getId());
        patientCrudRepository.deleteById(patientDto.getId());
        doctorCrudRepository.deleteById(doctorDto.getId());
    }

    @Test
    void updateVisit() {
    }

    @Test
    void getVisitById() {
    }

    @Test
    void getAllVisitsForPatient() {
    }

    @Test
    void getVisitsByCodeContains() {
    }
}