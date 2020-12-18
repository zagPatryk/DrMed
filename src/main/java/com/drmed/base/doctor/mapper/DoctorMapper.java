package com.drmed.base.doctor.mapper;

import com.drmed.base.doctor.domain.Doctor;
import com.drmed.base.doctor.dto.DoctorDto;
import com.drmed.base.doctor.dto.DoctorInfoDto;
import com.drmed.base.doctor.repository.DoctorHibernate;
import com.drmed.base.visit.mapper.VisitMapper;
import com.drmed.base.visit.repository.VisitHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {
    @Autowired
    private VisitMapper visitMapper;

    public DoctorHibernate mapToDoctorHibernate(Doctor doctor) {
        return new DoctorHibernate(
                doctor.getId(),
                doctor.getCode(),
                doctor.getFirstName(),
                doctor.getLastName(),
                visitMapper.mapToVisitHibernateList(doctor.getVisitList()),
                doctor.getDoctorStatus(),
                doctor.getEmail(),
                doctor.getTrelloBoardId()
        );
    }

    public Doctor mapToDoctor(DoctorHibernate doctorHibernate) {
        return new Doctor(
                doctorHibernate.getId(),
                doctorHibernate.getCode(),
                doctorHibernate.getFirstName(),
                doctorHibernate.getLastName(),
                doctorHibernate.getDoctorStatus(),
                doctorHibernate.getEmail(),
                doctorHibernate.getVisitHibernateList().stream().map(VisitHibernate::getId).collect(Collectors.toList()),
                doctorHibernate.getTrelloBoardId()
        );
    }

    public DoctorDto mapToDoctorDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getCode(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getDoctorStatus(),
                doctor.getEmail()
        );
    }

    public DoctorInfoDto mapToDoctorInfoDto(Doctor doctor) {
        return new DoctorInfoDto(
                doctor.getId(),
                doctor.getCode(),
                doctor.getFirstName(),
                doctor.getLastName()
        );
    }

    public List<DoctorInfoDto> mapToDoctorInfoDtoList(List<Doctor> doctorList) {
        return doctorList == null ? new ArrayList<>()
                : doctorList.stream().map(this::mapToDoctorInfoDto).collect(Collectors.toList());
    }

    public List<Doctor> mapToDoctorList(List<DoctorHibernate> doctorHibernateList) {
        return doctorHibernateList == null ? new ArrayList<>()
                : doctorHibernateList.stream().map(this::mapToDoctor).collect(Collectors.toList());
    }
}
