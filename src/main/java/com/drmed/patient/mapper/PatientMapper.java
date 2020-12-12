package com.drmed.patient.mapper;

import com.drmed.doctor.mapper.DoctorMapper;
import com.drmed.order.mapper.OrderMapper;
import com.drmed.order.repository.OrderHibernate;
import com.drmed.patient.domain.Patient;
import com.drmed.patient.dto.NewPatientDto;
import com.drmed.patient.dto.PatientDto;
import com.drmed.patient.dto.PatientInfoDto;
import com.drmed.patient.repository.PatientHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private OrderMapper orderMapper;

    public Patient mapToPatient(PatientHibernate patientHibernate) {
        return new Patient(
                patientHibernate.getId(),
                patientHibernate.getMRN(),
                patientHibernate.getFirstName(),
                patientHibernate.getLastName(),
                patientHibernate.getBirthDate(),
                patientHibernate.getDoctor().getId(),
                patientHibernate.getOrders().stream().map(OrderHibernate::getId).collect(Collectors.toList())
        );
    }

    public PatientHibernate mapToPatientHibernate(Patient patient) {
        return new PatientHibernate(
                patient.getId(),
                patient.getMRN(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                doctorMapper.mapToDoctorHibernate(patient.getDoctor()),
                orderMapper.mapToOrderHibernate(patient.getOrders())
        );
    }

    public PatientInfoDto mapToPatientInfoDto(Patient patient) {
        return new PatientInfoDto(
                patient.getId(),
                patient.getMRN(),
                patient.getFirstName(),
                patient.getLastName()
        );
    }

    public PatientDto mapToPatientDto(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getMRN(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                doctorMapper.mapToDoctorInfoDto(patient.getDoctor()),
                orderMapper.mapToOrderInfoDtoList(patient.getOrders())
        );
    }

    public Patient mapToPatient(PatientDto patientDto) {
        return new Patient(
                patientDto.getId(),
                patientDto.getMRN(),
                patientDto.getFirstName(),
                patientDto.getLastName(),
                patientDto.getBirthDate(),
                patientDto.getDoctor().getId(),
                new ArrayList<>()
        );
    }

    public Patient mapToPatient(NewPatientDto newPatientDto) {
        return new Patient(
                newPatientDto.getMRN(),
                newPatientDto.getFirstName(),
                newPatientDto.getLastName(),
                newPatientDto.getBirthDate(),
                newPatientDto.getDoctorId()
        );
    }

    public List<PatientInfoDto> mapToPatientInfoDtoList(List<Patient> patientList) {
        return patientList.stream().map(this::mapToPatientInfoDto).collect(Collectors.toList());
    }

    public List<PatientHibernate> mapToPatientHibernateList(List<Patient> patientList) {
        return patientList.stream().map(this::mapToPatientHibernate).collect(Collectors.toList());
    }

    public List<Patient> mapToPatientList(List<PatientHibernate> patientHibernateList) {
        return patientHibernateList.stream().map(this::mapToPatient).collect(Collectors.toList());
    }
}
