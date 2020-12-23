package com.drmed.base.patient.mapper;

import com.drmed.base.patient.domain.Patient;
import com.drmed.base.patient.dto.PatientDto;
import com.drmed.base.patient.dto.PatientInfoDto;
import com.drmed.base.patient.repository.PatientHibernate;
import com.drmed.base.visit.mapper.VisitMapper;
import com.drmed.base.visit.repository.VisitHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
    @Autowired
    private VisitMapper visitMapper;

    public Patient mapToPatient(PatientHibernate patientHibernate) {
        return new Patient(
                patientHibernate.getId(),
                patientHibernate.getCode(),
                patientHibernate.getFirstName(),
                patientHibernate.getLastName(),
                patientHibernate.getBirthDate(),
                patientHibernate.getGender(),
                patientHibernate.getVisits().stream().map(VisitHibernate::getId).collect(Collectors.toList())
        );
    }

    public PatientHibernate mapToPatientHibernate(Patient patient) {
        return new PatientHibernate(
                patient.getId(),
                patient.getCode(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                patient.getGender(),
                visitMapper.mapToVisitHibernateList(patient.getVisitList())
        );
    }

    public PatientInfoDto mapToPatientInfoDto(Patient patient) {
        return new PatientInfoDto(
                patient.getId(),
                patient.getCode(),
                patient.getFirstName(),
                patient.getLastName()
        );
    }

    public PatientDto mapToPatientDto(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getCode(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                patient.getGender(),
                visitMapper.mapToVisitInfoDtoList(patient.getVisitList())
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
