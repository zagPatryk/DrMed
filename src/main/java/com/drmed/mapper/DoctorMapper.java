package com.drmed.mapper;

import com.drmed.domain.doctor.Doctor;
import com.drmed.domain.doctor.DoctorDto;
import com.drmed.domain.patient.Patient;
import com.drmed.repository.DoctorRepository;
import com.drmed.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public Doctor mapToDoctor(DoctorDto doctorDto) {
        Doctor doctor  = new Doctor();
        if (doctorDto.getId() != null) {
            Optional<Doctor> doctorFromDb = doctorRepository.findById(doctorDto.getId());
            if (doctorFromDb.isPresent()) {
                doctor = doctorFromDb.get();
            }
        }
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setPrimaryId(doctorDto.getPrimaryId());
        for (Long patientId : doctorDto.getPatientsIds()) {
            Optional<Patient> optionalPatient = patientRepository.findById(patientId);
            if (optionalPatient.isPresent()) {
                doctor.getPatients().add(optionalPatient.get());
            }
        }
        return doctor;
    }

    public DoctorDto mapToDoctorDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getPrimaryId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getPatients().stream()
                        .map(Patient::getId)
                        .collect(Collectors.toList())
        );
    }

    public List<DoctorDto> mapToDoctorDtoList(List<Doctor> doctorList) {
        return doctorList.stream().map(this::mapToDoctorDto).collect(Collectors.toList());
    }
}
