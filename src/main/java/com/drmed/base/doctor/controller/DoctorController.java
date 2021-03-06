package com.drmed.base.doctor.controller;

import com.drmed.base.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.base.doctor.dto.NewDoctorDto;
import com.drmed.base.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "getDoctorById")
    public DoctorDto getDoctorsById(@RequestParam Long doctorId) throws DataNotFoundInDatabase {
        return doctorService.getDoctorDtoById(doctorId);
    }

    @GetMapping(value = "getAllDoctors")
    public List<DoctorInfoDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping(value = "getDoctorByPrimaryIdContains")
    public List<DoctorInfoDto> getDoctorByPrimaryIdContains(@RequestParam String primaryId) {
        return doctorService.getDoctorByPrimaryIdContains(primaryId);
    }

    @GetMapping(value = "getDoctorByFirstNameContains")
    public List<DoctorInfoDto> getDoctorByFirstNameContains(@RequestParam String firstName) {
        return doctorService.getDoctorByFirstNameContains(firstName);
    }

    @GetMapping(value = "getDoctorByLastNameContains")
    public List<DoctorInfoDto> getDoctorByLastNameContains(@RequestParam String lastName) {
        return doctorService.getDoctorByLastNameContains(lastName);
    }

    @GetMapping(value = "getDoctorByFirstAndLastNameContains")
    public List<DoctorInfoDto> getDoctorByFirstAndLastNameContains(@RequestParam String firstName, @RequestParam String lastName) {
        return doctorService.getDoctorByFirstNameContainsAndLastNameContaining(firstName, lastName);
    }

    @PostMapping(value = "addNewDoctor")
    public DoctorDto addNewDoctor(@RequestBody NewDoctorDto newDoctorDto) {
        return doctorService.addNewDoctor(newDoctorDto);
    }

    @PutMapping(value = "updateDoctor")
    public DoctorDto updateDoctor(@RequestBody DoctorDto doctorDto) throws DataNotFoundInDatabase {
        return doctorService.updateDoctor(doctorDto);
    }

    @PutMapping(value = "safeDeleteDoctorById")
    public DoctorDto safeDeleteDoctorById(@RequestParam Long doctorId) throws DataNotFoundInDatabase {
        return doctorService.safeDeleteDoctorById(doctorId);
    }
}