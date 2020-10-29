package com.drmed.controler;

import com.drmed.domain.doctor.DoctorDto;
import com.drmed.mapper.DoctorMapper;
import com.drmed.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorMapper doctorMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getDoctorById")
    public DoctorDto getDoctorsById(@RequestParam Long doctorId) {
        return doctorMapper.mapToDoctorDto(doctorService.findDoctorById(doctorId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllDoctors")
    public List<DoctorDto> getAllDoctors() {
        return doctorMapper.mapToDoctorDtoList(doctorService.findAllDoctors());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addNewDoctor", consumes = APPLICATION_JSON_VALUE)
    public DoctorDto addNewDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorMapper.mapToDoctorDto(doctorService.saveDoctor(doctorMapper.mapToDoctor(doctorDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateDoctor", consumes = APPLICATION_JSON_VALUE)
    public DoctorDto updateDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorMapper.mapToDoctorDto(doctorService.saveDoctor(doctorMapper.mapToDoctor(doctorDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "deleteDoctorById")
    public DoctorDto safeDeleteDoctorById(@RequestParam Long doctorId) {
        return doctorMapper.mapToDoctorDto(doctorService.deleteDoctorById(doctorId));
    }
}
