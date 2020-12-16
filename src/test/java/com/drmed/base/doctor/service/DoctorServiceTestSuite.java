package com.drmed.base.doctor.service;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.DoctorNotFoundException;
import com.drmed.base.additional.statuses.ActivityStatus;
import com.drmed.base.doctor.domain.Doctor;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.base.doctor.repository.DoctorCrudRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DoctorServiceTestSuite {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorCrudRepository doctorCrudRepository;

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
        //assertNotNull(doctorFromBase.getTrelloBoardId()); // mocka dodać

        // Clean
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
    public void updateDoctor() {
    }

    @Test
    public void removeVisitFromDoctorList() {
    }

    @Test
    public void safeDeleteDoctorById() {
    }
}