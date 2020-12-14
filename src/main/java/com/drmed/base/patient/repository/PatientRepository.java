package com.drmed.base.patient.repository;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.PatientNotFoundException;
import com.drmed.base.patient.domain.Patient;
import com.drmed.base.patient.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientRepository {
    @Autowired
    private PatientCrudRepository patientCrudRepository;
    @Autowired
    private PatientMapper patientMapper;

    public Patient getPatientById(Long patientId) throws PatientNotFoundException {
        return patientMapper.mapToPatient(patientCrudRepository.findById(patientId).orElseThrow(PatientNotFoundException::new));
    }

    public List<Patient> getPatientsByCodeContains(String code) {
        List<PatientHibernate> patientHibernateList = new ArrayList<>();
        patientCrudRepository.findByCodeContains(code).forEach(patientHibernateList::add);
        return patientMapper.mapToPatientList(patientHibernateList);
    }

    public List<Patient> getPatientsByFirstNameContains(String firstname) {
        List<PatientHibernate> patientHibernateList = new ArrayList<>();
        patientCrudRepository.findByFirstNameContains(firstname).forEach(patientHibernateList::add);
        return patientMapper.mapToPatientList(patientHibernateList);
    }

    public List<Patient> getPatientsByLastNameContains(String lastName) {
        List<PatientHibernate> patientHibernateList = new ArrayList<>();
        patientCrudRepository.findByLastNameContains(lastName).forEach(patientHibernateList::add);
        return patientMapper.mapToPatientList(patientHibernateList);
    }

    public List<Patient> getPatientsByFirstNameContainsAndLastNameContains(String firstName, String lastName) {
        List<PatientHibernate> patientHibernateList = new ArrayList<>();
        patientCrudRepository.findByFirstNameContainsAndLastNameContains(firstName, lastName).forEach(patientHibernateList::add);
        return patientMapper.mapToPatientList(patientHibernateList);
    }

    public List<Patient> getAllPatients() {
        List<PatientHibernate> patientHibernateList = new ArrayList<>();
        patientCrudRepository.findAll().forEach(patientHibernateList::add);
        return patientMapper.mapToPatientList(patientHibernateList);
    }

    public Patient savePatient(Patient patient) {
        PatientHibernate patientHibernate = patientMapper.mapToPatientHibernate(patient);
        patientCrudRepository.save(patientHibernate);
        return patientMapper.mapToPatient(patientHibernate);
    }
}