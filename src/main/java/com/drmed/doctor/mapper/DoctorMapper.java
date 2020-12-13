package com.drmed.doctor.mapper;

import com.drmed.doctor.domain.Doctor;
import com.drmed.doctor.dto.DoctorDto;
import com.drmed.doctor.dto.DoctorInfoDto;
import com.drmed.doctor.repository.DoctorHibernate;
import com.drmed.patient.mapper.PatientMapper;
import com.drmed.patient.repository.PatientHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {
    @Autowired
    private PatientMapper patientMapper;

    public DoctorHibernate mapToDoctorHibernate(Doctor doctor) {
        return new DoctorHibernate(
                doctor.getId(),
                doctor.getPrimaryId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                patientMapper.mapToPatientHibernateList(doctor.getPatientList()),
                doctor.getDoctorStatus(),
                doctor.getEmail(),
                doctor.getTrelloBoardId()
        );
    }

    public Doctor mapToDoctor(DoctorDto doctorDto) {
        return new Doctor(
                doctorDto.getId(),
                doctorDto.getPrimaryId(),
                doctorDto.getFirstName(),
                doctorDto.getLastName(),
                doctorDto.getDoctorStatus(),
                doctorDto.getEmail(),
                doctorDto.getPatientsIds(),
                ""
        );
    }

    public Doctor mapToDoctor(DoctorHibernate doctorHibernate) {
        return new Doctor(
                doctorHibernate.getId(),
                doctorHibernate.getPrimaryId(),
                doctorHibernate.getFirstName(),
                doctorHibernate.getLastName(),
                doctorHibernate.getDoctorStatus(),
                doctorHibernate.getEmail(),
                doctorHibernate.getPatients().stream().map(PatientHibernate::getId).collect(Collectors.toList()),
                doctorHibernate.getTrelloBoardId()
        );
    }

    public DoctorDto mapToDoctorDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getPrimaryId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getDoctorStatus(),
                doctor.getEmail(),
                doctor.getPatientsIds()
        );
    }

    public DoctorInfoDto mapToDoctorInfoDto(Doctor doctor) {
        return new DoctorInfoDto(
                doctor.getId(),
                doctor.getPrimaryId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getDoctorStatus()
        );
    }

    public List<DoctorInfoDto> mapToDoctorInfoDtoList(List<Doctor> doctorList) {
        return doctorList.stream().map(this::mapToDoctorInfoDto).collect(Collectors.toList());
    }

    public List<Doctor> mapToDoctorList(List<DoctorHibernate> doctorHibernateList) {
        return doctorHibernateList.stream().map(this::mapToDoctor).collect(Collectors.toList());
    }
}
