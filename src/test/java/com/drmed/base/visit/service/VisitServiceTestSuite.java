package com.drmed.base.visit.service;

import com.drmed.api.apimedic.diagnosis.dto.DiagnosisDto;
import com.drmed.api.apimedic.diagnosis.repository.DiagnosisCrudRepository;
import com.drmed.api.apimedic.diagnosis.service.DiagnosisService;
import com.drmed.api.apimedic.symptoms.dto.SymptomDto;
import com.drmed.api.apimedic.symptoms.service.SymptomService;
import com.drmed.base.additional.Gender;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class VisitServiceTestSuite {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private SymptomService symptomService;
    @Autowired
    private DiagnosisService diagnosisService;
    @Autowired
    private PatientCrudRepository patientCrudRepository;
    @Autowired
    private DoctorCrudRepository doctorCrudRepository;
    @Autowired
    private VisitCrudRepository visitCrudRepository;
    @Autowired
    private DiagnosisCrudRepository diagnosisCrudRepository;

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
    void addDiagnosisForVisit() throws DoctorNotFoundException, PatientNotFoundException, VisitNotFoundException, NoSuchAlgorithmException, InvalidKeyException {
        // Given
        NewPatientDto newPatientDto = new NewPatientDto("patientCode", "firstName",
                "lastName", LocalDate.of(1999,12,1), Gender.FEMALE);
        PatientDto patientDto = patientService.addNewPatient(newPatientDto);

        NewDoctorDto newDoctorDto = new NewDoctorDto();
        DoctorDto doctorDto = doctorService.addNewDoctor(newDoctorDto);

        if (symptomService.getAllSymptomDtoList().size() == 0) {
            symptomService.downloadSymptomsToBase();
        }
        SymptomDto symptomDto = symptomService.getAllSymptomDtoList().get(0);
        List<SymptomDto> symptomList = new ArrayList<>();
        symptomList.add(symptomDto);

        NewVisitDto newVisitDto = new NewVisitDto("visitCode", LocalDate.of(1999,1,1),
                patientDto.getId(), doctorDto.getId(), symptomList);

        VisitDto visitDto = visitService.addNewVisit(newVisitDto);

        // When
        DiagnosisDto diagnosisDto = diagnosisService.createDiagnosisForVisit(visitDto.getId());

        // Then
        Assertions.assertNotNull(diagnosisDto.getIcdCode());
        Assertions.assertNotNull(diagnosisDto.getIcdName());
        Assertions.assertNotNull(diagnosisDto.getName());
        Assertions.assertNotNull(diagnosisDto.getProfessionalName());
        Assertions.assertNotNull(diagnosisDto.getSpecialistNameList());

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